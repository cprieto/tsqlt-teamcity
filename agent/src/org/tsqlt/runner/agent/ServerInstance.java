package org.tsqlt.runner.agent;

import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Map;

public class ServerInstance {
    private final String server;
    private final String instance;

    public ServerInstance(@NotNull String input) {
        if (input.contains("\\")) {
            String[] parsed = input.split("\\\\", 2);
            server = parsed[0];
            instance = parsed[1];
        } else {
            server = input;
            instance = null;
        }
    }

    public String getServer(){
        return server;
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

    public static ServerInstance create(Map<String, String> options){
        return new ServerInstance(options.get(PropertyNames.USER_INSTANCE));
    }
}
