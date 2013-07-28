package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.Collection;

public class CollectionHelper {
    public static boolean contains(final String propertyName, Collection<InvalidProperty> properties) {
        return CollectionUtils.exists(properties, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                InvalidProperty property = (InvalidProperty) o;
                return property.getPropertyName() == propertyName;
            }
        });
    }
}
