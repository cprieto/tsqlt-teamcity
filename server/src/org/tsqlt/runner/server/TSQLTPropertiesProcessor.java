package org.tsqlt.runner.server;

import jetbrains.buildServer.log.Loggers;
import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.util.*;

public class TSQLTPropertiesProcessor implements PropertiesProcessor {
    private final List<Validator> validators;

    public TSQLTPropertiesProcessor(List<Validator> validators) {
        this.validators = validators == null || validators.isEmpty() ? new Vector<Validator>() : validators;
    }

    @Override
    public Collection<InvalidProperty> process(@NotNull Map<String, String> properties) {
        List<InvalidProperty> invalidProperties = new Vector<InvalidProperty>();
        for (Validator validator : validators){
            InvalidProperty error = validator.hasErrors(properties);
            if (error == null) continue;

            Loggers.SERVER.warn(String.format("[tSQLt Plugin] invalid property %s : %s",
                    error.getPropertyName(), error.getInvalidReason()));

            invalidProperties.add(error);
        }

        return invalidProperties;
    }
}
