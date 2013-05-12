package org.tsqlt.runner.agent;

import jetbrains.buildServer.agent.BuildProgressLogger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RunAllExecutor implements QueryExecutor {
    private final BuildProgressLogger logger;

    public RunAllExecutor(BuildProgressLogger logger) {
        this.logger = logger;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        Statement query = connection.createStatement();
        logger.progressStarted("Executing all tSQLt tests in database");
        boolean success = query.execute(SqlCommands.EXECUTE_ALL_TESTS);
        logger.progressFinished();
    }
}
