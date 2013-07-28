package org.tsqlt.runner.server;

import org.tsqlt.runner.common.PropertyNames;

import java.util.Map;

public class PasswordValidator implements Validator {
    @Override
    public String hasErrors(Map<String, String> properties) {
        String password = properties.get(PropertyNames.USER_PASSWORD);
        if (password == null || password.isEmpty())
            return "You need to specify a password for the user";

        return null;
    }
}
