package org.tsqlt.runner.agent;

import jetbrains.buildServer.agent.BuildAgentConfiguration;
import jetbrains.buildServer.util.Bitness;
import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Vector;

public class SqlServerConnectionBuilder implements ConnectionBuilder {
    private final JtdsConnectionStringBuilder builder;
    private final String user;
    private final String password;
    private final boolean useWindowsAuth;

    public SqlServerConnectionBuilder(@NotNull Map<String, String> properties){
        ServerInstance server = ServerInstanceImpl.create(properties);
        DomainUser domainUser = DomainUser.create(properties);
        String database = properties.get(PropertyNames.DATABASE);
        OptionParser options = new OptionParser(properties.get(PropertyNames.OPTIONS));

        useWindowsAuth = domainUser.getUseNtlm();
        password = properties.get(PropertyNames.USER_PASSWORD);
        user = domainUser.getUser();

        builder = new JtdsConnectionStringBuilder(server, database, domainUser.getDomain(), options.getOptions(), useWindowsAuth);
    }

    @NotNull
    @Override
    public Connection getConnection(@NotNull BuildAgentConfiguration configuration) throws SQLException, ClassNotFoundException {

        Class.forName("net.sourceforge.jtds.jdbc.Driver");

        if (useWindowsAuth)
            addNativeToJavaPath(getSSOLibraryPath(configuration));

        return useWindowsAuth
                ? DriverManager.getConnection(builder.toString())
                : DriverManager.getConnection(builder.toString(), user, password);
    }

    private void addNativeToJavaPath(String path){
        String libraryPath = System.getProperty("java.library.path");
        Vector<String> paths = new Vector<String>(Arrays.asList(libraryPath.split(";")));
        if (paths.contains(path))
            return;

        System.setProperty("java.library.path", libraryPath + ";" + path);
        try {
            Field fieldSysPaths = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPaths.setAccessible(true);
            fieldSysPaths.set(null, null);
        } catch (NoSuchFieldException e) {
            // Loggers.AGENT.error(e);
        } catch (IllegalAccessException e) {
            // Loggers.AGENT.error(e);
        }
    }

    @NotNull
    private String getSSOLibraryPath(@NotNull BuildAgentConfiguration configuration){
        String lib = configuration.getAgentPluginsDirectory().getAbsolutePath();

        String architecture = configuration.getSystemInfo().bitness() == Bitness.BIT32 ? "x86" : "x64";
        return String.format("%s\\tsqlt-agent\\lib\\native\\%s\\", lib, architecture);
    }
}
