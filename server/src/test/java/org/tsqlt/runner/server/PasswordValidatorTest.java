package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import org.testng.annotations.Test;
import org.tsqlt.runner.common.PropertyNames;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

public class PasswordValidatorTest {
    private final Validator sut = new PasswordValidator();
    @Test
    public void testItWillFailWithEmptyPassword(){
        Map<String, String> properties = new HashMap<String, String>();
        InvalidProperty error = sut.hasErrors(properties);

        assertNotNull(error);
        assertEquals(error.getPropertyName(), PropertyNames.USER_PASSWORD);
    }

    @Test
    public void testItWontFailWithNonEmptyPassword(){
        Map<String, String> properties = new HashMap<String, String>(){{
            put(PropertyNames.USER_PASSWORD, "password");
        }};
        InvalidProperty error = sut.hasErrors(properties);

        assertNull(error);
    }
}
