package org.tsqlt.runner.agent;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;
import jetbrains.buildServer.log.Loggers;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executors;

public class TSQLTAgentBuildRunner implements AgentBuildRunner {
    @NotNull
    @Override
    public BuildProcess createBuildProcess(@NotNull AgentRunningBuild agentRunningBuild,
                                           @NotNull BuildRunnerContext buildRunnerContext) throws RunBuildException {
        Loggers.AGENT.info("Requesting process");
        return new TSQLTBuildProcess(Executors.newSingleThreadExecutor());
    }

    @NotNull
    @Override
    public AgentBuildRunnerInfo getRunnerInfo() {
        Loggers.AGENT.info("Requesting info");
        return new TSQLTAgentBuildRunnerInfo();
    }
}

