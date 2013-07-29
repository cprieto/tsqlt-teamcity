package org.tsqlt.runner.server;

import jetbrains.buildServer.log.Loggers;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.serverSide.RunType;
import jetbrains.buildServer.serverSide.RunTypeRegistry;
import org.tsqlt.runner.common.PluginConstants;

import java.util.List;
import java.util.Map;

public class TSQLTRunType extends RunType {
    private final PropertiesProcessor propertiesProcessor;
    public TSQLTRunType(final RunTypeRegistry registry, final PropertiesProcessor processor) {
        registry.registerRunType(this);
        propertiesProcessor = processor;
    }

    @Override
    public String getType() {
        return PluginConstants.RUNNER_TYPE;
    }

    @Override
    public String getDisplayName() {
        return PluginConstants.RUNNER_NAME;
    }

    @Override
    public String getDescription() {
        return PluginConstants.RUNNER_DESCRIPTION;
    }

    @Override
    public PropertiesProcessor getRunnerPropertiesProcessor() {
        return propertiesProcessor;
    }

    @Override
    public String getEditRunnerParamsJspFilePath() {
        return ViewNames.EDIT_PARAMS;
    }

    @Override
    public String getViewRunnerParamsJspFilePath() {
        return ViewNames.VIEW_PARAMS;
    }

    @Override
    public Map<String, String> getDefaultRunnerProperties() {
        return null;
    }
}
