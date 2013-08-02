package org.tsqlt.runner.agent;

import jetbrains.buildServer.log.Loggers;
import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class SqlServerConnectionBuilder implements ConnectionBuilder {
    private final JtdsConnectionStringBuilder builder;
    private final String user;
    private final String password;

    public SqlServerConnectionBuilder(@NotNull Map<String, String> properties){
        ServerInstance server = ServerInstance.create(properties);
        DomainUser domainUser = DomainUser.create(properties);
        String database = properties.get(PropertyNames.DATABASE);
        OptionParser options = new OptionParser(properties.get(PropertyNames.OPTIONS));
        password = properties.get(PropertyNames.USER_PASSWORD);
        user = domainUser.getUser();

        builder = new JtdsConnectionStringBuilder(server, database, domainUser.getDomain(), options.getOptions());
    }

    @NotNull
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Loggers.AGENT.debug(String.format("[tSQLt Agent] connection string is %s", builder.toString()));

        Class.forName("net.sourceforge.jtds.jdbc.Driver");

        return DriverManager.getConnection(builder.toString(), user, password);
    }
}
