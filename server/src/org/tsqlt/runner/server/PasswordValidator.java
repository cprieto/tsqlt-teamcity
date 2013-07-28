package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Map;

public class PasswordValidator implements Validator {
    @Override
    public InvalidProperty hasErrors(Map<String, String> properties) {
        String password = properties.get(PropertyNames.USER_PASSWORD);
        if (password == null || password.isEmpty())
            return new InvalidProperty(PropertyNames.USER_PASSWORD, "You need to specify a password for the user");

        return null;
    }
}
