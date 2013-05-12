package org.tsqlt.runner.agent;

import com.sun.istack.internal.NotNull;
import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;
import jetbrains.buildServer.log.Loggers;

public class TSQLTAgentBuildRunner implements AgentBuildRunner {

    public TSQLTAgentBuildRunner(){
    }

    @Override
    public BuildProcess createBuildProcess(@NotNull AgentRunningBuild agentRunningBuild,
                                           @NotNull BuildRunnerContext buildRunnerContext) throws RunBuildException {
        Loggers.AGENT.info("Requesting process");
        return new TSQLTBuildProcess(agentRunningBuild, buildRunnerContext);
    }

    @Override
    public AgentBuildRunnerInfo getRunnerInfo() {
        Loggers.AGENT.info("Requesting info");
        return new TSQLTAgentBuildRunnerInfo();
    }
}

