package org.tsqlt.runner.agent;

import com.sun.istack.internal.NotNull;
import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;
import jetbrains.buildServer.log.Loggers;

public class TSQLTAgentBuildRunner implements AgentBuildRunner {

    public TSQLTAgentBuildRunner(){
        Loggers.AGENT.info("Is somebody calling me?");
    }

    @Override
    public BuildProcess createBuildProcess(@NotNull AgentRunningBuild agentRunningBuild,
                                           @NotNull BuildRunnerContext buildRunnerContext) throws RunBuildException {
        return new TSQLTBuildProcess(agentRunningBuild);
    }

    @Override
    public AgentBuildRunnerInfo getRunnerInfo() {
        return new TSQLTAgentBuildRunnerInfo();
    }
}

