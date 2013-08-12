package org.tsqlt.runner.server;

import jetbrains.buildServer.log.Loggers;
import jetbrains.buildServer.requirements.Requirement;
import jetbrains.buildServer.requirements.RequirementType;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.serverSide.RunType;
import jetbrains.buildServer.serverSide.RunTypeRegistry;
import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PluginConstants;
import org.tsqlt.runner.common.PropertyNames;

import java.util.List;
import java.util.Map;
import java.util.Vector;

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

    private boolean needWindowsAuth(@NotNull Map<String, String> properties) {
        if (properties.containsKey(PropertyNames.WINDOWS_AUTH) == false)
            return false;

        boolean needWinAuth = Boolean.parseBoolean(properties.get(PropertyNames.WINDOWS_AUTH));
        return needWinAuth;
    }

    @NotNull
    @Override
    public List<Requirement> getRunnerSpecificRequirements(@NotNull Map<String, String> runParameters) {
        List<Requirement> requirements =  new Vector<Requirement>();

        if (needWindowsAuth(runParameters))
            requirements.add(new Requirement("teamcity.agent.jvm.os.name", "Windows", RequirementType.CONTAINS));

        return requirements;
    }
}
