package org.tsqlt.runner.agent;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ServerInstanceTest {
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
}
