package org.tsqlt.runner.agent;

public interface SqlCommands {
    String EXECUTE_ALL_TESTS = "EXEC [tSQLt].[RunAll]";
    String EXECUTE_TESTS_TEMPLATE = "EXEC [tSQLt].[Run] '{0}'";
    String QUERY_RESULTS = "SELECT * FROM [tSQLt].[TestResult] ORDER BY [Class], [Name]";
    String QUERY_RESULTS_TEMPLATE = "SELECT * FROM [tSQLt].[TestResult] WHERE ([Name] = '{0}' OR [Class]= '{0}') ORDER BY [Class], [Name]";
}