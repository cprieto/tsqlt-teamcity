package org.tsqlt.runner.agent;

import org.testng.annotations.Test;
import org.tsqlt.runner.common.PropertyNames;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class DomainUserTest {
    @Test
    public void testItCanGetServer(){
        DomainUser sut = new DomainUser("user");

        assertNull(sut.getDomain());
        assertEquals(sut.getUser(), "user");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    @SuppressWarnings({"UnusedDeclaration", "ConstantConditions"})
    public void testItThrowsIfInputIsNull(){
        DomainUser sut = new DomainUser(null);
    }

    @Test
    public void testItCanParseUserDomain(){
        DomainUser sut = new DomainUser("domain\\user");

        assertEquals(sut.getUser(), "user");
        assertEquals(sut.getDomain(), "domain");
    }

    @Test
    public void testItWouldTellYouYesWhenHasDomain(){
        DomainUser sut = new DomainUser("domain\\user");

        assertTrue(sut.hasDomain());
    }

    @Test
    public void testItShouldTellYouNoWhenOnlyUser(){
        DomainUser sut = new DomainUser("user");

        assertFalse(sut.hasDomain());
    }

    @Test
    public void testItCanReturnToStringWithOnlyUser(){
        DomainUser sut = new DomainUser("user");

        assertEquals(sut.toString(), "user");
    }

    @Test
    public void testItCanReturnToStringWithUserAndDomain(){
        DomainUser sut = new DomainUser("domain\\user");

        assertEquals(sut.toString(), "domain\\user");
    }

    @Test
    public void testItCanBeCreatedFromProperties(){
        Map<String, String> properties = new HashMap<String, String>() {{
            put(PropertyNames.USER_DOMAIN, "user");
        }};
        DomainUser sut = DomainUser.create(properties);

        assertNotNull(sut);
    }
}