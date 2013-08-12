package org.tsqlt.runner.agent;

import jetbrains.buildServer.agent.BuildAgentConfiguration;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionBuilder {
    Connection getConnection(@NotNull BuildAgentConfiguration configuration) throws SQLException, ClassNotFoundException;
}
