package org.tsqlt.runner.agent;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public final class TestCaseParser {
    @NotNull
    public static Map<String, List<TestCase>> fromResultSet(@NotNull ResultSet rows) throws SQLException {
        Map<String, List<TestCase>> results = new HashMap<String, List<TestCase>>();

        while (rows.next()) {
            String suite = rows.getString(TestCase.SUITE_KEY);
            TestCase result = new TestCase(
                    rows.getString(TestCase.TEST_KEY),
                    rows.getString(TestCase.MESSAGE_KEY),
                    rows.getString(TestCase.RESULT_KEY));
            addResult(results, result, suite);
        }

        return results;
    }

    private static void addResult(@NotNull Map<String, List<TestCase>> results,
                                  @NotNull final TestCase result,
                                  @NotNull String suite) {
        if (results.containsKey(suite)) {
            results.get(suite).add(result);
            return;
        }

        results.put(suite, new Vector<TestCase>() {{
            add(result);
        }});
    }
}
