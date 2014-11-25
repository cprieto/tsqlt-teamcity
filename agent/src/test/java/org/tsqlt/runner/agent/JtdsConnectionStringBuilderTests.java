package org.tsqlt.runner.agent;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class JtdsConnectionStringBuilderTests {

    private ServerInstance server;
    private JtdsConnectionStringBuilder sut;

    @Test
    public void testItCanBuildConnectionStringWithDefaults() {
        server = mock(ServerInstance.class, new ServerAnswer());
        sut = new JtdsConnectionStringBuilder(server, "database");

        assertEquals(sut.toString(), "jdbc:jtds:sqlserver://server:1433/database");
    }

    @Test
    public void testItCorrectlyUsesNonDefaultPort() {
        server = mock(ServerInstance.class, new ServerAnswer());
        when(server.getPort()).thenReturn(1234);

        sut = new JtdsConnectionStringBuilder(server, "database");

        assertEquals(sut.toString(), "jdbc:jtds:sqlserver://server:1234/database");
    }

    @Test
    public void testItPassInstanceAsServerOption() {
        server = mock(ServerInstance.class, new ServerAnswer());
        when(server.hasInstance()).thenReturn(true);
        when(server.getInstance()).thenReturn("instancename");

        sut = new JtdsConnectionStringBuilder(server, "database");

        assertEquals(sut.toString(), "jdbc:jtds:sqlserver://server:1433/database;instance=instancename");
    }

    @Test
    public void testItCanHandleDomainAsOption() {
        server = mock(ServerInstance.class, new ServerAnswer());
        sut = new JtdsConnectionStringBuilder(server, "database", "domainname");

        assertEquals(sut.toString(), "jdbc:jtds:sqlserver://server:1433/database;domain=domainname");
    }

    @Test
    public void testItCanHandleAdditionalOptions() {
        Map<String, String> options = new HashMap<String, String>() {{
            put("option1", "value1");
            put("option2", "value2");
        }};

        server = mock(ServerInstance.class, new ServerAnswer());
        sut = new JtdsConnectionStringBuilder(server, "database", null, options, false);

        assertEquals(sut.toString(), "jdbc:jtds:sqlserver://server:1433/database;option1=value1;option2=value2");
    }

    @Test
    public void testItCanBuildConnectionStringWhenUsingWindowsAuth(){
        server = mock(ServerInstance.class, new ServerAnswer());
        sut = new JtdsConnectionStringBuilder(server, "database", null, null, true);

        assertEquals(sut.toString(), "jdbc:jtds:sqlserver://server:1433/database;useNTLMv2=true");
    }

    class ServerAnswer implements Answer {

        @Override
        public Object answer(InvocationOnMock invocation) throws Throwable {
            String method = invocation.getMethod().getName();
            if (method == "getPort")
                return 1433;
            if (method == "getServer")
                return "server";
            if (method == "hasInstance")
                return false;

            return null;
        }
    }
}
