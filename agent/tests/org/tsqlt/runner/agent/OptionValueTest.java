package org.tsqlt.runner.agent;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class OptionValueTest {
    @Test
    public void testItCanParseOptionValue(){
        OptionValue sut = new OptionValue("option=value");

        assertEquals(sut.getOption(), "option");
        assertEquals(sut.getValue(), "value");
    }

    @Test
    public void testItIsValidIfOptionValueIsPresent(){
        OptionValue sut = new OptionValue("option=value");

        assertTrue(sut.isValid());
    }

    @Test
    public void testItIsInvalidWhenInvalid(){
        OptionValue sut = new OptionValue("nothing");

        assertFalse(sut.isValid());
    }

    @Test
    public void testItIsInvalidIfManyValues(){
        OptionValue sut = new OptionValue("nothing=is=valid");

        assertFalse(sut.isValid());
    }

    @Test
    public void testItTrimValueOptions(){
        OptionValue sut = new OptionValue("option = value");

        assertEquals(sut.getOption(), "option");
        assertEquals(sut.getValue(), "value");
    }

    @Test
    public void testItReturnsInvalidWhenIsSpace(){
        OptionValue sut = new OptionValue(" =invalid");

        assertFalse(sut.isValid());
    }
}
