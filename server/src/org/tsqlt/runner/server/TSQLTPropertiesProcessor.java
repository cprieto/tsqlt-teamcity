package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TSQLTPropertiesProcessor implements PropertiesProcessor {
    @Override
    public Collection<InvalidProperty> process(Map<String, String> properties) {
        List<InvalidProperty> invalidProperties = new Vector<InvalidProperty>();
        if (isEmpty(properties, PropertyNames.CONNECTION_STRING))
            invalidProperties.add(new InvalidProperty(
                    PropertyNames.CONNECTION_STRING,
                    "You need to specify JDBC connection string"));

        return invalidProperties;
    }

    private boolean isEmpty(Map<String, String> map, String key) {
        return map.containsKey(key) == false || map.get(key).isEmpty() == false;
    }
}
