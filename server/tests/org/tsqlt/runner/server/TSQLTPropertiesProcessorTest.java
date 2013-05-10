package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Collection;
import java.util.HashMap;

import static junit.framework.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TSQLTPropertiesProcessorTest {
    private PropertiesProcessor processor;

    @BeforeTest
    public void setUp(){
        processor = new TSQLTPropertiesProcessor();
    }

    @Test
    public void testProcessShouldReportEmptyConnectionString() throws Exception {
        HashMap<String, String> properties = new HashMap<String, String>();
        Collection<InvalidProperty> invalid = processor.process(properties);

        boolean hasProperty = hasInvalid(invalid, PropertyNames.CONNECTION_STRING);
        assertTrue(hasProperty);
    }

    @Test
    public void testProcessPassWithValidConnectionString() throws Exception {
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put(PropertyNames.CONNECTION_STRING, "jdbc://database/stuff");

        Collection<InvalidProperty> invalid = processor.process(properties);
        boolean hasKey = hasInvalid(invalid, PropertyNames.CONNECTION_STRING);
        assertFalse(hasKey);
    }

    private boolean hasInvalid(Collection<InvalidProperty> properties, final String key) {
        return CollectionUtils.exists(properties, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                InvalidProperty property = (InvalidProperty) o;
                return property.getPropertyName() == key;
            }
        });
    }
}