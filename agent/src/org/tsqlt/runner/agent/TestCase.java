package org.tsqlt.runner.agent;

public final class TestCase {
    private final String test;
    private final String message;
    private final TestResult result;

    public static final String TEST_KEY = "TestCase";
    public static final String MESSAGE_KEY = "Msg";
    public static final String RESULT_KEY = "Result";
    public static final String SUITE_KEY = "TestSuite";

    public TestCase(String test, String message, String result) {
        this.test = test;
        this.message = message;
        this.result = TestResult.fromString(result);
    }

    public String getTest() {
        return test;
    }

    public String getMessage() {
        return message;
    }

    public TestResult getResult() {
        return result;
    }
}

