package org.tsqlt.runner.agent;

import jetbrains.buildServer.agent.BuildFinishedStatus;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;

public class TSQLTBuildProcess extends FutureBasedBuildAdapter {
    protected TSQLTBuildProcess(@NotNull ExecutorService executor) {
        super(executor);
    }

    @Override
    public BuildFinishedStatus call() throws Exception {
        return null;
    }
}
