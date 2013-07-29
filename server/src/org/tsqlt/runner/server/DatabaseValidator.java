package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Map;

public class DatabaseValidator implements Validator {
    @Override
    public InvalidProperty hasErrors(Map<String, String> properties) {
        String database = properties.get(PropertyNames.DATABASE);
        if (database == null || database.isEmpty())
            return new InvalidProperty(PropertyNames.DATABASE, "The name of the database cannot be empty.");
        return null;
    }
}
