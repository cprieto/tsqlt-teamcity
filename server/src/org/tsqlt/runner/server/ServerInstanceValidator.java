package org.tsqlt.runner.server;

import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Map;

public class ServerInstanceValidator implements Validator {
    @Override
    public String hasErrors(@NotNull Map<String, String> properties) {
        String server = properties.get(PropertyNames.SERVER_INSTANCE);
        if (server == null || server.trim().isEmpty())
            return "You need to specify a valid server (address or name)";

        if (server.split("\\\\").length > 2)
            return "The correct format is server\\instance";

        return null;
    }
}
