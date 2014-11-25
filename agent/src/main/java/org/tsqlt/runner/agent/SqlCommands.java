package org.tsqlt.runner.agent;

public interface SqlCommands {
    String EXECUTE_ALL_TESTS = "EXEC [tSQLt].[RunAll]";
    String QUERY_RESULTS = "SELECT * FROM [tSQLt].[TestResult] ORDER BY [Class]";
}