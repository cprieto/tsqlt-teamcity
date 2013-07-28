package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;

import java.util.Map;

public interface Validator {
    InvalidProperty hasErrors(Map<String, String> properties);
}