package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import org.testng.annotations.Test;
import org.tsqlt.runner.common.PropertyNames;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class DatabaseValidatorTest {
    private final Validator sut = new DatabaseValidator();
    @Test
    public void testItShouldCheckThatDatabaseCannotBeNull(){
        Map<String, String> properties = new HashMap<String, String>();
        InvalidProperty error = sut.hasErrors(properties);

        assertNotNull(error);
        assertEquals(error.getPropertyName(), PropertyNames.DATABASE);
    }

    @Test
    public void testItShouldPassWhenDatabaseIsNotEmpty(){
        Map<String, String> properties = new HashMap<String, String>() {{
            put(PropertyNames.DATABASE, "database");
        }};
        InvalidProperty error = sut.hasErrors(properties);

        assertNull(error);
    }
}
