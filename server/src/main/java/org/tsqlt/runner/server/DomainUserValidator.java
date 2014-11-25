package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Map;

public class DomainUserValidator extends CredentialValidator implements Validator {
    @Override
    public InvalidProperty hasErrors(@NotNull Map<String, String> properties) {

        if (needsWindowsAuthentication(properties)) return null;

        String user = properties.get(PropertyNames.USER_DOMAIN);
        if (user == null || user.trim().isEmpty())
            return new InvalidProperty(PropertyNames.USER_DOMAIN,
                    "You need to specify a user or domain\\user");

        if (user.split("\\\\").length > 2)
            return new InvalidProperty(PropertyNames.USER_DOMAIN,
                    "The correct format is domain\\user, and no more than one domain!");

        return null;
    }
}

