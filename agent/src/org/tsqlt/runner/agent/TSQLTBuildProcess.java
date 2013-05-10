package org.tsqlt.runner.agent;

import jetbrains.buildServer.log.Loggers;
import org.jetbrains.annotations.NotNull;
import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.AgentRunningBuild;
import jetbrains.buildServer.agent.BuildFinishedStatus;
import jetbrains.buildServer.agent.BuildProcess;
import jetbrains.buildServer.agent.BuildProgressLogger;
import jetbrains.buildServer.messages.DefaultMessagesInfo;

public class TSQLTBuildProcess implements BuildProcess {
    private final BuildProgressLogger logger;

    public TSQLTBuildProcess(@NotNull AgentRunningBuild agentRunningBuild) {
        logger = agentRunningBuild.getBuildLogger();
    }

    @Override
    public void start() throws RunBuildException {
        Loggers.AGENT.warn("This shit started");

        logger.activityStarted("tSQLt", DefaultMessagesInfo.BLOCK_TYPE_TEST_SUITE);
        logger.logSuiteStarted("Sample Suite");

        logger.logTestStarted("Test 1");
        logger.logTestFinished("Test 1");

        logger.logTestStarted("Test 2");
        logger.logTestFailed("Shit happens", "Yes", "but that is good");
        logger.logTestFinished("Test 2");

        logger.logSuiteFinished("Sample Suite");
        logger.activityStarted("tSQLt", DefaultMessagesInfo.BLOCK_TYPE_TEST_SUITE);

        Loggers.AGENT.warn("This shit should be finished");
    }

    @Override
    public boolean isInterrupted() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isFinished() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void interrupt() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BuildFinishedStatus waitFor() throws RunBuildException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
