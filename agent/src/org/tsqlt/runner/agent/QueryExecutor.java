package org.tsqlt.runner.agent;

import java.sql.Connection;
import java.sql.SQLException;

public interface QueryExecutor {
    void execute(Connection connection) throws SQLException;
}

