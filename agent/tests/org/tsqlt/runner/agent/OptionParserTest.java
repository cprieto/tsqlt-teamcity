package org.tsqlt.runner.agent;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OptionParserTest {
    @Test
    public void testItCanParseOneOption(){
        OptionParser sut = new OptionParser("option=value");

        assertTrue(sut.getOptions().containsKey("option"));
        assertEquals(sut.getOptions().get("option"), "value");
    }

    @Test
    public void testItCanParseManyOptions(){
        OptionParser sut = new OptionParser("option1=value1;option2=value2");

        assertEquals(sut.getOptions().size(), 2);
        assertTrue(sut.getOptions().containsKey("option2"));
        assertTrue(sut.getOptions().containsKey("option1"));
    }

    @Test
    public void testItCanHandleInvalidInput(){
        OptionParser sut = new OptionParser("option=value;bananas");

        assertEquals(sut.getOptions().size(), 1);
    }
}
