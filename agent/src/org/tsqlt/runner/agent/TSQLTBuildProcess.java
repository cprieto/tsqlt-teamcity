package org.tsqlt.runner.agent;

import jetbrains.buildServer.agent.BuildFinishedStatus;
import jetbrains.buildServer.agent.BuildProgressLogger;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class TSQLTBuildProcess extends FutureBasedBuildAdapter {
    private final BuildProgressLogger logger;
    private final ConnectionBuilder connectionBuilder;

    protected TSQLTBuildProcess(@NotNull ExecutorService executor, @NotNull BuildProgressLogger logger, @NotNull ConnectionBuilder connectionBuilder) {
        super(executor);

        this.logger = logger;
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public BuildFinishedStatus call() throws Exception {

        try {
            execute();
        } catch (SQLException e) {
            logger.buildFailureDescription(e.getMessage());
            return BuildFinishedStatus.FINISHED_FAILED;
        }

        return BuildFinishedStatus.FINISHED_SUCCESS;
    }

    private void execute() throws SQLException, ClassNotFoundException {
        Connection connection = connectionBuilder.getConnection();

        runAllTests(connection);

        ResultSet rows = getResults(connection);

        Map<String, List<TestCase>> results = TestCaseParser.fromResultSet(rows);
        reportResults(results);
        rows.getStatement().close();
        logger.progressFinished();

        connection.close();
    }

    private void runAllTests(@NotNull Connection connection) throws SQLException {
        logger.progressStarted("Running tests");
        PreparedStatement query = connection.prepareStatement(SqlCommands.EXECUTE_ALL_TESTS);
        try {
            query.execute();
            query.close();
        } catch (SQLException e) {
            if (e.getErrorCode() != 50000)
                throw e;
            logger.message(e.getMessage());
        } finally {
            logger.progressFinished();
        }
    }

    @NotNull
    private ResultSet getResults(@NotNull Connection connection) throws SQLException {
        logger.progressStarted("Getting test results");
        PreparedStatement query = connection.prepareStatement(SqlCommands.QUERY_RESULTS);
        return query.executeQuery();
    }

    private void logTest(@NotNull TestCase testCase) {
        final String test = testCase.getTest();
        logger.logTestStarted(test);

        if (testCase.getResult() != TestResult.SUCCESS) {
            logger.logTestFailed(test, testCase.getMessage(), null);
            return;
        }

        logger.logTestFinished(test);
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
