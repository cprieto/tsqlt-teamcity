package org.tsqlt.runner.agent;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class JtdsConnectionStringBuilder {
    private final String server;
    private final String instance;
    private final String database;
    private final String domain;
    private final int port;
    private final Map<String, String> options;

    public JtdsConnectionStringBuilder(@NotNull ServerInstance serverInstance, @NotNull String database, String domain, Map<String, String> options){
        server = serverInstance.getServer();
        port = serverInstance.getPort();
        instance = serverInstance.getInstance();
        this.database = database;
        this.domain = domain;
        this.options = options;
    }

    public JtdsConnectionStringBuilder(@NotNull ServerInstance serverInstance, @NotNull String database) {
        this(serverInstance, database, null, null);
    }

    public JtdsConnectionStringBuilder(@NotNull ServerInstance serverInstance, @NotNull String database, String domain){
        this(serverInstance, database, domain, null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("jdbc:jtds:sqlserver://");
        sb.append(String.format("%s:%s", server, port));
        sb.append(String.format("/%s", database));

        if (instance != null)
            sb.append(String.format(";instance=%s", instance));

        if (domain != null && !domain.isEmpty())
            sb.append(String.format(";domain=%s", domain));

        if (options != null && !options.isEmpty())
            sb.append(processOptions(options));

        return sb.toString();
    }

    private String processOptions(@NotNull Map<String, String> options){
        StringBuilder sb = new StringBuilder();
        Set<String> keys = options.keySet();
        for (String key : keys){
            sb.append(String.format(";%s=%s", key, options.get(key)));
        }

        return sb.toString();
    }
}
