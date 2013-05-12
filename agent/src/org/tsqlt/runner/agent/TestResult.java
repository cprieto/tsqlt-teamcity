package org.tsqlt.runner.agent;

import java.util.Collection;

public class TestResult {
    private final String testName;
    private final String message;

    public TestStatus getStatus() {
        return status;
    }

    public String getTestName() {
        return testName;
    }

    public String getMessage() {
        return message;
    }

    private final TestStatus status;

    public TestResult(String testName, String message, TestStatus status) {
        this.testName = testName;
        this.message = message;
        this.status = status;
    }
}
