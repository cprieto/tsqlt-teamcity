package org.tsqlt.runner.agent;

import jetbrains.buildServer.agent.BuildAgentConfiguration;
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
    private final BuildAgentConfiguration configuration;
    @NotNull
    private final RuntimeOptions runtimeOptions;

    protected TSQLTBuildProcess(@NotNull ExecutorService executor,
                                @NotNull BuildProgressLogger logger,
                                @NotNull BuildAgentConfiguration configuration,
                                @NotNull ConnectionBuilder connectionBuilder,
                                @NotNull RuntimeOptions runtimeOptions) {
        super(executor);

        this.logger = logger;
        this.connectionBuilder = connectionBuilder;
        this.configuration = configuration;
        this.runtimeOptions = runtimeOptions;
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
        Connection connection = connectionBuilder.getConnection(configuration);
        ResultSet rows = null;
        if(runtimeOptions.RunAllTests()) {
        runAllTests(connection);
            rows = getResults(connection, "");
        }
        else
        {
            runTests(connection, runtimeOptions.getTestsToRun());
            rows = getResults(connection, runtimeOptions.getTestsToRun());
        }

        Map<String, List<TestCase>> results = TestCaseParser.fromResultSet(rows);
        reportResults(results);
        rows.getStatement().close();
        logger.progressFinished();

        connection.close();
    }

    private void runAllTests(@NotNull Connection connection) throws SQLException {
        this.runCommand(connection, SqlCommands.EXECUTE_ALL_TESTS);
    }
    private void runTests(@NotNull Connection connection, String tests) throws SQLException {
        String command = SqlCommands.EXECUTE_TESTS_TEMPLATE.replace("{0}", tests);
        this.runCommand(connection, command);
    }
    private void runCommand(@NotNull Connection connection, String command) throws SQLException {
        logger.progressStarted("Running tests");
        PreparedStatement query = connection.prepareStatement(command);
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
    private ResultSet getResults(@NotNull Connection connection, String tests) throws SQLException {
        logger.progressStarted("Getting test results");
        PreparedStatement query = null;
        if(tests == null || tests.isEmpty()) {
            query = connection.prepareStatement(SqlCommands.QUERY_RESULTS);
        }
        else
        {
            String command = SqlCommands.QUERY_RESULTS_TEMPLATE.replace("{0}", tests);
            query = connection.prepareStatement(command);
        }
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
