package org.tsqlt.runner.agent;

import org.jetbrains.annotations.NotNull;

public enum TestResult {
    SUCCESS,
    FAILURE,
    ERROR;

    public static TestResult fromString(@NotNull String value){
        return valueOf(value.trim().toUpperCase());
    }
}
