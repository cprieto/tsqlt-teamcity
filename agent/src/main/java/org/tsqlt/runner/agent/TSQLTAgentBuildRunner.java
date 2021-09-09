package org.tsqlt.runner.agent;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;
import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Map;
import java.util.concurrent.Executors;

public class TSQLTAgentBuildRunner implements AgentBuildRunner {
    @NotNull
    @Override
    public BuildProcess createBuildProcess(@NotNull AgentRunningBuild agentRunningBuild,
                                           @NotNull BuildRunnerContext buildRunnerContext) throws RunBuildException {

        Map<String, String> properties = buildRunnerContext.getRunnerParameters();
        ConnectionBuilder connectionBuilder = new SqlServerConnectionBuilder(properties);
        RuntimeOptions runtimeOptions = new RuntimeOptions(properties.get(PropertyNames.TESTS));
        return new TSQLTBuildProcess(
                Executors.newSingleThreadExecutor(),
                agentRunningBuild.getBuildLogger(),
                buildRunnerContext.getBuild().getAgentConfiguration(),
                connectionBuilder,
                runtimeOptions);
    }

    @NotNull
    @Override
    public AgentBuildRunnerInfo getRunnerInfo() {
        // Loggers.AGENT.debug("Requesting info");
        return new TSQLTAgentBuildRunnerInfo();
    }
}

