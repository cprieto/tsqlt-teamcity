package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static org.testng.Assert.assertTrue;
import static org.tsqlt.runner.server.CollectionHelper.contains;

public class TSQLTPropertiesProcessorTest {
    private PropertiesProcessor processor;

    @Test
    public void testItCanValidateWithOneValidator() {
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
}

