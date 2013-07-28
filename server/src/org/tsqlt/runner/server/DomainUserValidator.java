package org.tsqlt.runner.server;

import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Map;

public class DomainUserValidator implements Validator {
    @Override
    public String hasErrors(@NotNull Map<String, String> properties) {
        String user = properties.get(PropertyNames.USER_DOMAIN);
        if (user == null || user.trim().isEmpty())
            return "You need to specify a user or domain\\user";

        if (user.split("\\\\").length > 2)
            return "The correct format is domain\\user, and no more than one domain!";

        return null;
    }
}
