package org.tsqlt.runner.agent;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ResultTestTests {
    @Test
    public void testItParsesFailure(){
        TestResult result = TestResult.fromString("Failure");
        assertEquals(result, TestResult.FAILURE);
    }

    @Test
    public void testItCanParseEvenWithSpaces(){
        TestResult result = TestResult.fromString("Failure ");
        assertEquals(result, TestResult.FAILURE);
    }

    @Test
    public void testItParsesSuccess(){
        TestResult result = TestResult.fromString("Success");
        assertEquals(result, TestResult.SUCCESS);
    }

    @Test
    public void testItParsesError() {
        TestResult result = TestResult.fromString("Error");
        assertEquals(result, TestResult.ERROR);
    }
}