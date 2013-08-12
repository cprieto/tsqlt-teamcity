package org.tsqlt.runner.agent;

import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Map;

public class ServerInstanceImpl implements ServerInstance {
    private String server;
    private int port;
    private final String instance;

    public ServerInstanceImpl(@NotNull String input) {
        if (input.contains("\\")) {
            String[] parsed = input.split("\\\\", 2);
            setServer(parsed[0]);
            instance = parsed[1];
        } else {
            setServer(input);
            instance = null;
        }
    }

    @Override
    @NotNull
    public String getServer(){
        return server;
    }

    private void setServer(@NotNull String server) {
        if (server.contains(":")) {
            String[] parsed = server.split(":", 2);
            this.server = transformLocalAddress(parsed[0]);
            this.port = Integer.parseInt(parsed[1]);
        } else {
            this.server = transformLocalAddress(server);
            port = 1433;
        }
    }

    @NotNull
    public static String transformLocalAddress(@NotNull String input) {
        if (input.toLowerCase().equals("(local)") || input.trim().equals("."))
            return "127.0.0.1";
        return input;
    }

    @Override
    public String getInstance(){
        return instance;
    }

    @Override
    public boolean hasInstance(){
        return instance != null && !instance.isEmpty();
    }

    @Override
    public String toString() {
        return hasInstance() ? String.format("%s\\%s", server, instance) : server;
    }

    @NotNull
    public static ServerInstance create(@NotNull Map<String, String> options){
        return new ServerInstanceImpl(options.get(PropertyNames.SERVER_INSTANCE));
    }

    @Override
    public int getPort() {
        return port;
    }
}
