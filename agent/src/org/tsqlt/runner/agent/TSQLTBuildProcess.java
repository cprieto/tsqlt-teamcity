package org.tsqlt.runner.agent;

import jetbrains.buildServer.agent.*;
import jetbrains.buildServer.log.Loggers;
import org.jetbrains.annotations.NotNull;
import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.messages.DefaultMessagesInfo;
import org.tsqlt.runner.common.PluginConstants;
import org.tsqlt.runner.common.PropertyNames;

import java.sql.*;
import java.util.Map;

public class TSQLTBuildProcess implements BuildProcess {
    private final BuildProgressLogger logger;
    private final Map<String, String> properties;
    private final SqlServerConnectionBuilder connectionBuilder;
    private boolean failed = false;

    public TSQLTBuildProcess(@NotNull AgentRunningBuild agentRunningBuild,
                             @NotNull BuildRunnerContext context) {
        logger = agentRunningBuild.getBuildLogger();
        properties = context.getRunnerParameters();

        for (String prop : properties.keySet()) {
            Loggers.AGENT.info(String.format("[tSQLt Agent] %s : %s", prop, properties.get(prop)));
        }

        connectionBuilder = new SqlServerConnectionBuilder(properties);
    }

    @Override
    public void start() throws RunBuildException {
        failed = false;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection connection = connectionBuilder.getConnection();
            //runAll(connection);
        } catch (ClassNotFoundException e){
            logger.error("jTDS driver not found");
            failed = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            failed = true;
        }
    }

    private void runAll(Connection connection) throws SQLException {
        logger.progressStarted("Running all tSQLt tests");

        Statement query = connection.createStatement();
        try {
            query.execute(SqlCommands.EXECUTE_ALL_TESTS);
        } catch (SQLException e) {
            processException(e, connection);
            throw e;
        }
        query.close();

        processTestResults(connection);

        logger.progressFinished();
    }

    private void processTestResults(Connection connection) {
        try {
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(SqlCommands.QUERY_RESULTS);
            reportResults(results);
            query.close();
        } catch (SQLException e) {
            logger.error(String.format("Error retrieving results: %s", e.getMessage()));
        }
    }

    private void reportResults(ResultSet results) throws SQLException {
        String previousSuite = null;

        while (results.next()){
            String suite = results.getString("Class");
            if (previousSuite == null || suite != previousSuite){
                startNewSuite(previousSuite, suite);
            }

            String test = results.getString("TestCase");
            String result = results.getString("Result");
            String message = results.getString("Msg");
            Loggers.AGENT.info(String.format("Result is %s", result));
            logger.logTestStarted(test);

            if (result.equalsIgnoreCase("Failure"))
                logger.logTestFailed(test, message, null);

            logger.logTestFinished(test);
        }

        if (previousSuite != null)
            logger.logSuiteFinished(previousSuite);

        results.close();
    }

    private void startNewSuite(String previousSuite, String suite) {
        if (previousSuite != null)
            logger.logSuiteFinished(previousSuite);
        logger.logSuiteStarted(suite);
    }

    private void processException(SQLException e, Connection connection){
        if (e.getErrorCode() == 50000)
            processTestResults(connection);
    }

    private Connection getConnection() throws SQLException {
        String connectionString = properties.get(PropertyNames.CONNECTION_STRING);
        return DriverManager.getConnection(connectionString);
    }

    @Override
    public boolean isInterrupted() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void interrupt() {
    }

    @Override
    public BuildFinishedStatus waitFor() throws RunBuildException {
        if (failed)
            return BuildFinishedStatus.FINISHED_FAILED;
        return null;
    }
}
