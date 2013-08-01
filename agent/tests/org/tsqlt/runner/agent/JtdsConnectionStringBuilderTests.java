package org.tsqlt.runner.agent;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;


public class JtdsConnectionStringBuilderTests {
    @Test
    public void testItCanBuildConnectionStringUsingServer(){
        ServerInstance server = new ServerInstance("server");
        JtdsConnectionStringBuilder sut = new JtdsConnectionStringBuilder(server, "database");

        assertEquals(sut.toString(), "jdbc:jtds:sqlserver://server/database");
    }

    @Test
    public void testItPassInstanceAsServerOption(){
        ServerInstance server = new ServerInstance("server\\instancename");
        JtdsConnectionStringBuilder sut = new JtdsConnectionStringBuilder(server, "database");

        assertEquals(sut.toString(), "jdbc:jtds:sqlserver://server/database;instance=instancename");
    }

    @Test
    public void testItCanHandleDomainAsOption(){
        JtdsConnectionStringBuilder sut = new JtdsConnectionStringBuilder(new ServerInstance("server"), "database", "domainname");

        assertEquals(sut.toString(), "jdbc:jtds:sqlserver://server/database;domain=domainname");
    }

    @Test
    public void testItCanHandleAdditionalOptions(){
        Map<String, String> options = new HashMap<String, String>() {{
            put("option1", "value1");
            put("option2", "value2");
        }};

        JtdsConnectionStringBuilder sut = new JtdsConnectionStringBuilder(new ServerInstance("server"), "database", null, options);

        assertEquals(sut.toString(), "jdbc:jtds:sqlserver://server/database;option1=value1;option2=value2");
    }

    @Test
    public void testItCanBuildFullConnectionString(){
        Map<String, String> options = new HashMap<String, String>() {{
            put("option1", "value1");
            put("option2", "value2");
        }};

        JtdsConnectionStringBuilder sut = new JtdsConnectionStringBuilder(new ServerInstance("server\\instance"), "database", "domain", options);

        assertEquals(sut.toString(), "jdbc:jtds:sqlserver://server/database;instance=instance;domain=domain;option1=value1;option2=value2");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    @SuppressWarnings({"UnusedDeclaration", "ConstantConditions"})
    public void testItShouldThrowBecauseServerCannotBeNull(){
        JtdsConnectionStringBuilder sut = new JtdsConnectionStringBuilder(null, "database");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    @SuppressWarnings({"UnusedDeclaration", "ConstantConditions"})
    public void testItShouldThrowBecauseDatabaseCannotBeNull(){
        JtdsConnectionStringBuilder sut = new JtdsConnectionStringBuilder(new ServerInstance("server"), null);
    }
}
