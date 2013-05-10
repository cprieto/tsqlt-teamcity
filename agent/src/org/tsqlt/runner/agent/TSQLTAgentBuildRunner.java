package org.tsqlt.runner.agent;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.;

public class TSQLTAgentBuildRunner implements AgentBuildRunner {
    @Override
    public BuildProcess createBuildProcess(@NotNull AgentRunningBuild agentRunningBuild, @NotNull BuildRunnerContext buildRunnerContext) throws RunBuildException {
        return null;
    }

    @Override
    public AgentBuildRunnerInfo getRunnerInfo() {
        return null;
    }
}
