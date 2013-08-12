package org.tsqlt.runner.agent;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class JtdsConnectionStringBuilder {
    private final String server;
    private final String instance;
    private final String database;
    private final String domain;
    private final int port;
    private final Map<String, String> options;
    private final boolean useWindowsAuth;

    public JtdsConnectionStringBuilder(@NotNull ServerInstance serverInstance, @NotNull String database, String domain, Map<String, String> options, boolean useWindowsAuth) {
        server = serverInstance.getServer();
        port = serverInstance.getPort();
        instance = serverInstance.getInstance();

        this.database = database;
        this.domain = domain;
        this.options = options;
        this.useWindowsAuth = useWindowsAuth;
    }

    public JtdsConnectionStringBuilder(@NotNull ServerInstance serverInstance, @NotNull String database) {
        this(serverInstance, database, null, null, false);
    }

    public JtdsConnectionStringBuilder(@NotNull ServerInstance serverInstance, @NotNull String database, String domain) {
        this(serverInstance, database, domain, null, false);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("jdbc:jtds:sqlserver://");
        sb.append(server).append(":").append(port);
        sb.append("/").append(database);

        if (instance != null && !instance.isEmpty())
            sb.append(";instance=").append(instance);

        if (domain != null && !domain.isEmpty())
            sb.append(";domain=").append(domain);

        if (options != null && !options.isEmpty())
            for (String option : OptionHelper.filter(options.keySet()))
                sb.append(";").append(option).append("=").append(options.get(option));

        if (useWindowsAuth)
            sb.append(";useNTLMv2=true");

        return sb.toString();
    }
}
