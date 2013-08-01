package org.tsqlt.runner.agent;

import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Map;

public class ServerInstance {
    private String server;
    private int port;
    private final String instance;

    public ServerInstance(@NotNull String input) {
        if (input.contains("\\")) {
            String[] parsed = input.split("\\\\", 2);
            setServer(parsed[0]);
            instance = parsed[1];
        } else {
            setServer(input);
            instance = null;
        }
    }

    public String getServer(){
        return server;
    }

    private void setServer(String server) {
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

    public String getInstance(){
        return instance;
    }

    public boolean hasInstance(){
        return instance != null && !instance.isEmpty();
    }

    @Override
    public String toString() {
        return hasInstance() ? String.format("%s\\%s", server, instance) : server;
    }

    public static ServerInstance create(@NotNull Map<String, String> options){
        return new ServerInstance(options.get(PropertyNames.SERVER_INSTANCE));
    }

    public int getPort() {
        return port;
    }
}
