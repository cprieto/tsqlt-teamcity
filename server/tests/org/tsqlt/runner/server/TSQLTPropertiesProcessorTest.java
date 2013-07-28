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
import java.util.Map;
import java.util.Vector;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class TSQLTPropertiesProcessorTest {
    private PropertiesProcessor processor;

    @Test
    public void testItCanValidateWithOneValidator(){
        Vector<Validator> validators = new Vector<Validator>() {{
            add(new Validator() {
                @Override
                public InvalidProperty hasErrors(Map<String, String> properties) {
                    return new InvalidProperty("Sample", "Error");
                }
            });
        }};

        Map<String, String> properties = new HashMap<String, String>();

        processor = new TSQLTPropertiesProcessor(validators);
        Collection<InvalidProperty> invalidProperties = processor.process(properties);

        assertTrue(contains("Sample", invalidProperties));
    }

    private boolean contains(final String propertyName, Collection<InvalidProperty> properties) {
        return CollectionUtils.exists(properties, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                InvalidProperty property = (InvalidProperty) o;
                return property.getPropertyName() == propertyName;
            }
        });
    }
}