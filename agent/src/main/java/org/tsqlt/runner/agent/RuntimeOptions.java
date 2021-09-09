package org.tsqlt.runner.agent;

public class RuntimeOptions {

    public RuntimeOptions(String testsToRun)
    {
        this.testsToRun = testsToRun;
    }

    private final String testsToRun;

    public boolean RunAllTests(){
        return testsToRun == null || testsToRun.isEmpty();
    }

    public String getTestsToRun() {
        return testsToRun;
    }
}
