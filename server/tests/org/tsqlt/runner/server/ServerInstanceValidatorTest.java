package org.tsqlt.runner.server;

import org.testng.annotations.Test;
import org.tsqlt.runner.common.PropertyNames;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class ServerInstanceValidatorTest {
    private final Validator sut = new ServerInstanceValidator();

    @Test
    public void testItCanValidateWhenIsEmpty(){
        Map<String, String> properties = new HashMap<String, String>();
        String error = sut.hasErrors(properties);

        assertNotNull(error);
    }

    @Test
    public void testItCanValidateIfMoreThanOneInstance(){
        Map<String, String> properties = new HashMap<String, String>() {{
           put(PropertyNames.SERVER_INSTANCE, "server\\is\\invalid");
        }};
        String error = sut.hasErrors(properties);

        assertNotNull(error);
    }

    @Test
    public void testItDoesNotReturnErrorIfEverythingIsOk(){
        Map<String, String> properties = new HashMap<String, String>() {{
            put(PropertyNames.SERVER_INSTANCE, "server\\instance");
        }};
        String error = sut.hasErrors(properties);

        assertNull(error);
    }

    @Test
    public void testItCanPassJustTheServer(){
        Map<String, String> properties = new HashMap<String, String>() {{
            put(PropertyNames.SERVER_INSTANCE, "server");
        }};
        String error = sut.hasErrors(properties);

        assertNull(error);
    }
}
