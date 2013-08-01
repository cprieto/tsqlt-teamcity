package org.tsqlt.runner.agent;

import jetbrains.buildServer.agent.BuildFinishedStatus;
import jetbrains.buildServer.agent.BuildProgressLogger;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class TSQLTBuildProcess extends FutureBasedBuildAdapter {
    private final BuildProgressLogger logger;

    protected TSQLTBuildProcess(@NotNull ExecutorService executor, @NotNull BuildProgressLogger logger) {
        super(executor);

        this.logger = logger;
    }

    @Override
    public BuildFinishedStatus call() throws Exception {

        try {
            execute();
        } catch (SQLException e) {
            logger.buildFailureDescription("SQL Execution error: " + e.getMessage());
            return BuildFinishedStatus.FINISHED_FAILED;
        }

        return BuildFinishedStatus.FINISHED_SUCCESS;
    }

    private void execute() throws SQLException {
            Connection connection = null;
            runAllTests(connection);

            ResultSet rows = getResults(connection);
            Map<String, List<TestCase>> results = TestCaseParser.fromResultSet(rows);
            reportResults(results);
            rows.getStatement().close();
            logger.progressFinished();

            connection.close();
    }

    private void runAllTests(Connection connection) throws SQLException {
        logger.progressStarted("Running tests");
        PreparedStatement query = connection.prepareStatement(SqlCommands.EXECUTE_ALL_TESTS);
        query.execute();
        query.close();
        logger.progressFinished();
    }

    private ResultSet getResults(Connection connection) throws SQLException {
        logger.progressStarted("Getting test results");
        PreparedStatement query = connection.prepareStatement(SqlCommands.QUERY_RESULTS);
        return query.executeQuery();
    }

    private void logTest(@NotNull TestCase testCase){
        logger.logTestStarted(testCase.getTest());

        if (testCase.getResult() != TestResult.SUCCESS){
            logger.logTestFailed(testCase.getTest(), testCase.getMessage(), null);
            return;
        }

        logger.logTestFinished(testCase.getTest());
    }

    private void reportResults(@NotNull Map<String, List<TestCase>> results) {
        for (String suite : results.keySet()) {
            logger.logSuiteStarted(suite);

            for (TestCase testCase : results.get(suite)) {
                logTest(testCase);
            }

            logger.logSuiteFinished(suite);
        }
    }
}
