package org.tsqlt.runner.agent;

import com.sun.istack.internal.NotNull;
import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;

public class TSQLTAgentBuildRunner implements AgentBuildRunner {
    @Override
    public BuildProcess createBuildProcess(@NotNull AgentRunningBuild agentRunningBuild,
                                           @NotNull BuildRunnerContext buildRunnerContext) throws RunBuildException {
        return new TSQLTBuildProcess();
    }

    @Override
    public AgentBuildRunnerInfo getRunnerInfo() {
        return new TSQLTAgentBuildRunnerInfo();
    }
}

