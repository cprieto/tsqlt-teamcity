package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TSQLTPropertiesProcessor implements PropertiesProcessor {
    @Override
    public Collection<InvalidProperty> process(Map<String, String> stringStringMap) {
        List<InvalidProperty> invalidProperties = new Vector<InvalidProperty>();
        return invalidProperties;
    }
}
