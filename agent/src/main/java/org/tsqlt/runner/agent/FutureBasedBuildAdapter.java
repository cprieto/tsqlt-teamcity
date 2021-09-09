package org.tsqlt.runner.agent;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.BuildFinishedStatus;
import jetbrains.buildServer.agent.BuildProcessAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

public abstract class FutureBasedBuildAdapter extends BuildProcessAdapter implements Callable<BuildFinishedStatus> {
    private volatile Future<BuildFinishedStatus> status;
    private final ExecutorService executor;

    protected FutureBasedBuildAdapter(@NotNull ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public void start() throws RunBuildException {
        status = executor.submit(this);
    }

    @Override
    public boolean isInterrupted() {
        return status.isCancelled() && isFinished();
    }

    @Override
    public boolean isFinished() {
        return status.isDone();
    }

    @Override
    public void interrupt() {
        // Loggers.AGENT.warn("Attempting to interrupt!");
        status.cancel(true);
    }

    @NotNull
    @Override
    public BuildFinishedStatus waitFor() throws RunBuildException {
        try {
            return status.get();
        } catch (final InterruptedException e) {
            throw new RunBuildException(e);
        } catch (final ExecutionException e) {
            throw new RunBuildException(e);
        } catch (final CancellationException e) {
            // Loggers.AGENT.warn("Process was interrupted!");
            return BuildFinishedStatus.INTERRUPTED;
        }
    }
}
