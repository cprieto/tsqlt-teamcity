package org.tsqlt.runner.agent;

import org.jetbrains.annotations.NotNull;

public interface ServerInstance {
    @NotNull
    String getServer();

    String getInstance();

    boolean hasInstance();

    int getPort();
}
