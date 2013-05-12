package org.tsqlt.runner.agent;

import java.util.Collection;

public class TestSuite {
    public String getName() {
        return name;
    }

    public Collection<TestResult> getTests() {
        return tests;
    }

    private final String name;
    private Collection<TestResult> tests;

    public TestSuite(String name, Collection<TestResult> tests) {
        this.name = name;
        this.tests = tests;
    }
}
