package org.tsqlt.runner.agent;

import com.sun.istack.internal.NotNull;
import jetbrains.buildServer.agent.AgentBuildRunnerInfo;
import jetbrains.buildServer.agent.BuildAgentConfiguration;
import jetbrains.buildServer.log.Loggers;
import org.tsqlt.runner.common.PluginConstants;

public class TSQLTAgentBuildRunnerInfo implements AgentBuildRunnerInfo {

    @Override
    public String getType() {
        return PluginConstants.RUNNER_TYPE;
    }

    @Override
    public boolean canRun(@NotNull BuildAgentConfiguration buildAgentConfiguration) {
        Loggers.AGENT.info("Somebody asked me if I can run this, I said yes");
        return true;
    }
}
