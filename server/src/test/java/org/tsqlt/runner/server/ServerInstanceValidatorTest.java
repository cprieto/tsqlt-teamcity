package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import org.testng.annotations.Test;
import org.tsqlt.runner.common.PropertyNames;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class ServerInstanceValidatorTest {
    private final Validator sut = new ServerInstanceValidator();

    @Test
    public void testItCanValidateWhenIsEmpty(){
        Map<String, String> properties = new HashMap<String, String>();
        InvalidProperty error = sut.hasErrors(properties);

        assertNotNull(error);
        assertEquals(error.getPropertyName(), PropertyNames.SERVER_INSTANCE);
    }

    @Test
    public void testItCanValidateIfMoreThanOneInstance(){
        Map<String, String> properties = new HashMap<String, String>() {{
           put(PropertyNames.SERVER_INSTANCE, "server\\is\\invalid");
        }};
        InvalidProperty error = sut.hasErrors(properties);

        assertNotNull(error);
        assertEquals(error.getPropertyName(), PropertyNames.SERVER_INSTANCE);
    }

    @Test
    public void testItDoesNotReturnErrorIfEverythingIsOk(){
        Map<String, String> properties = new HashMap<String, String>() {{
            put(PropertyNames.SERVER_INSTANCE, "server\\instance");
        }};
        InvalidProperty error = sut.hasErrors(properties);

        assertNull(error);
    }

    @Test
    public void testItCanPassJustTheServer(){
        Map<String, String> properties = new HashMap<String, String>() {{
            put(PropertyNames.SERVER_INSTANCE, "server");
        }};
        InvalidProperty error = sut.hasErrors(properties);

        assertNull(error);
    }
}
