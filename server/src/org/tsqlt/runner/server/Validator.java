package org.tsqlt.runner.server;

import java.util.Map;

public interface Validator {
    String hasErrors(Map<String, String> properties);
}