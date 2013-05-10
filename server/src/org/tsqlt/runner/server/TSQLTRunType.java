package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.serverSide.RunType;
import org.tsqlt.runner.common.PluginConstants;

import java.util.Map;

public class TSQLTRunType extends RunType {
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
        return new TSQLTPropertiesProcessor();
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
