package org.tsqlt.runner.agent;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.BuildFinishedStatus;
import jetbrains.buildServer.agent.BuildProcess;

public class TSQLTBuildProcess implements BuildProcess {

    @Override
    public void start() throws RunBuildException {
        //To change body of implemented methods use File | Settings | File Templates.
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
