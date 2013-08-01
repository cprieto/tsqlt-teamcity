package org.tsqlt.runner.agent;

import org.testng.annotations.Test;
import org.tsqlt.runner.common.PropertyNames;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class ServerInstanceTests {
    @Test
    public void testItCanParseServerAndInstance(){
        final String input = "server\\instance";
        ServerInstance sut = new ServerInstance(input);

        assertEquals(sut.getServer(), "server");
        assertEquals(sut.getInstance(), "instance");
    }

    @Test
    public void testItCanParseWhenOnlyServerIsDefined(){
        ServerInstance sut = new ServerInstance("server");

        assertEquals(sut.getServer(), "server");
        assertNull(sut.getInstance());
    }

    @Test
    public void testItWillReturnHasInstanceWhenInstanceIsPresent(){
        final String input = "server\\instance";
        ServerInstance sut = new ServerInstance(input);

        assertEquals(sut.getInstance(), "instance");
        assertTrue(sut.hasInstance());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    @SuppressWarnings({"UnusedDeclaration", "ConstantConditions"})
    public void testItShouldThrowIfInputIsNull(){
        ServerInstance sut = new ServerInstance(null);
    }

    @Test
    public void testItShouldReturnNoInstanceIfOnlyServer(){
        ServerInstance sut = new ServerInstance("server");

        assertNull(sut.getInstance());
        assertFalse(sut.hasInstance());
    }

    @Test
    public void testItCanReturnToStringWithOnlyServer(){
        ServerInstance sut = new ServerInstance("server");

        assertEquals(sut.toString(), "server");
    }

    @Test
    public void testItCanReturnToStringWithServerAndInstance(){
        ServerInstance sut = new ServerInstance("server\\instance");

        assertEquals(sut.toString(), "server\\instance");
    }

    @Test
    public void testItCanBeCreatedFromProperties(){
        Map<String, String> properties = new HashMap<String, String>() {{
            put(PropertyNames.SERVER_INSTANCE, "user");
        }};

        ServerInstance sut = ServerInstance.create(properties);
        assertNotNull(sut);
    }

    @Test
    public void testItWillReturnDefaultPortIfNotSpecified(){
        final String input = "server";
        ServerInstance sut = new ServerInstance(input);

        assertEquals(sut.getPort(), 1433);
    }

    @Test
    public void testItWillReturnSpecifiedPort(){
        final String input = "server:1211\\blah";
        ServerInstance sut = new ServerInstance(input);

        assertEquals(sut.getPort(), 1211);
        assertEquals(sut.getServer(), "server");
    }

    @Test
    public void testItCanTransformLocalAddress(){
        final String input = "(local)";
        ServerInstance sut = new ServerInstance(input);

        assertEquals(sut.getServer(), "127.0.0.1");
    }

    @Test
    public void testItCanTransformDotAddress(){
        final String input = ".\\SQLEXPRESS";
        ServerInstance sut = new ServerInstance(input);

        assertEquals(sut.getServer(), "127.0.0.1");
        assertEquals(sut.getInstance(), "SQLEXPRESS");
    }
}
