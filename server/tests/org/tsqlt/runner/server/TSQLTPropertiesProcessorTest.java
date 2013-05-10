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

        boolean hasProperty = CollectionUtils.exists(invalid, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                InvalidProperty property = (InvalidProperty) o;
                return property.getPropertyName() == PropertyNames.CONNECTION_STRING;
            }
        });

        assertTrue(hasProperty);
    }
}