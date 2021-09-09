package org.tsqlt.runner.agent;

import org.jetbrains.annotations.NotNull;
import jetbrains.buildServer.agent.AgentBuildRunnerInfo;
import jetbrains.buildServer.agent.BuildAgentConfiguration;
import org.tsqlt.runner.common.PluginConstants;

public class TSQLTAgentBuildRunnerInfo implements AgentBuildRunnerInfo {

    @NotNull
    @Override
    public String getType() {
        return PluginConstants.RUNNER_TYPE;
    }

    @Override
    public boolean canRun(@NotNull BuildAgentConfiguration buildAgentConfiguration) {
        return true;
    }
}
