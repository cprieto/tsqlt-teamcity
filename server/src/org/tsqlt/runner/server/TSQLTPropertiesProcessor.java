package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TSQLTPropertiesProcessor implements PropertiesProcessor {
    private static final Logger logger = Logger.getLogger(TSQLTPropertiesProcessor.class);
    private final List<Validator> validators;

    public TSQLTPropertiesProcessor(List<Validator> validators) {
        this.validators = validators == null || validators.isEmpty() ? new Vector<Validator>() : validators;
    }

    @Override
    public Collection<InvalidProperty> process(@NotNull Map<String, String> properties) {
        List<InvalidProperty> invalidProperties = new Vector<InvalidProperty>();
        for (Validator validator : validators) {
            InvalidProperty error = validator.hasErrors(properties);
            if (error == null) continue;

            logger.warn(String.format("[tSQLt Plugin] invalid property %s : %s",
                    error.getPropertyName(), error.getInvalidReason()));

            invalidProperties.add(error);
        }

        return invalidProperties;
    }
}
