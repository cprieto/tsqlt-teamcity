package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TSQLTPropertiesProcessor implements PropertiesProcessor {
    private final List<Validator> validators;

    public TSQLTPropertiesProcessor(List<Validator> validators) {
        this.validators = validators == null || validators.isEmpty() ? new Vector<>() : validators;
    }

    @Override
    public Collection<InvalidProperty> process(@NotNull Map<String, String> properties) {
        List<InvalidProperty> invalidProperties = new Vector<>();
        for (Validator validator : validators) {
            InvalidProperty error = validator.hasErrors(properties);
            if (error == null) continue;


            invalidProperties.add(error);
        }

        return invalidProperties;
    }
}
