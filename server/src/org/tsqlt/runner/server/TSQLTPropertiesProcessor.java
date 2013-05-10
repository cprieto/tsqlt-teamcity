package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TSQLTPropertiesProcessor implements PropertiesProcessor {
    @Override
    public Collection<InvalidProperty> process(@NotNull Map<String, String> properties) {
        List<InvalidProperty> invalidProperties = new Vector<InvalidProperty>();
        if (hasProperty(properties, PropertyNames.CONNECTION_STRING) == false)
            invalidProperties.add(new InvalidProperty(
                    PropertyNames.CONNECTION_STRING,
                    "You need to specify a JDBC connection string"));

        return invalidProperties;
    }

    private boolean hasProperty(@NotNull Map<String, String> map, @NotNull String key) {
        return map.containsKey(key) && map.get(key).isEmpty() == false;
    }
}
