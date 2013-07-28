package org.tsqlt.runner.agent;

import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class SqlServerConnectionBuilder {
    private final JtdsConnectionStringBuilder builder;
    private final String user;
    private final String password;

    public SqlServerConnectionBuilder(@NotNull Map<String, String> properties){
        ServerInstance server = ServerInstance.create(properties);
        DomainUser domainUser = DomainUser.create(properties);
        String database = properties.get(PropertyNames.DATABASE);
        OptionParser options = new OptionParser(properties.get(PropertyNames.OPTIONS));
        password = properties.get(properties.get(PropertyNames.USER_PASSWORD));
        user = domainUser.getUser();

        builder = new JtdsConnectionStringBuilder(server, database, domainUser.getDomain(), options.getOptions());
    }

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(builder.toString(), user, password);

        return connection;
    }
}
