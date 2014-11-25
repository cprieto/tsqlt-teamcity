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
        // Loggers.AGENT.debug("Requesting process");

        ConnectionBuilder connectionBuilder = new SqlServerConnectionBuilder(buildRunnerContext.getRunnerParameters());
        return new TSQLTBuildProcess(
                Executors.newSingleThreadExecutor(),
                agentRunningBuild.getBuildLogger(),
                buildRunnerContext.getBuild().getAgentConfiguration(),
                connectionBuilder);
    }

    @NotNull
    @Override
    public AgentBuildRunnerInfo getRunnerInfo() {
        // Loggers.AGENT.debug("Requesting info");
        return new TSQLTAgentBuildRunnerInfo();
    }
}

