package org.tsqlt.runner.server;

import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Map;

public abstract class CredentialValidator {
    protected boolean needsWindowsAuthentication(@NotNull Map<String, String> properties) {
        return properties.containsKey(PropertyNames.WINDOWS_AUTH) && Boolean.parseBoolean(properties.get(PropertyNames.WINDOWS_AUTH));
    }
}
