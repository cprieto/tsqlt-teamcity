package org.tsqlt.runner.agent;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.AgentRunningBuild;
import jetbrains.buildServer.agent.BuildProcess;
import jetbrains.buildServer.agent.BuildRunnerContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

public class TSQLTBuildProcessTests {
    private BuildProcess sut;
    private AgentRunningBuild agentRunningBuild;
    private BuildRunnerContext context;

    @BeforeTest
    public void setUp(){
        agentRunningBuild = mock(AgentRunningBuild.class);
        context = mock(BuildRunnerContext.class);

        sut = new TSQLTBuildProcess(null);
    }

    @Test
    public void testItShouldStartTask() throws RunBuildException {
        sut.start();
    }
}
