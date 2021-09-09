package org.tsqlt.runner.agent;

import java.util.List;
import java.util.Set;
import java.util.Vector;

public final class OptionHelper {
    private static final List<String> invalidOptions = new Vector<String>() {{
            add("domain");
            add("useNTMLv2");
            add("instance");
            add("user");
            add("password");
    }};

    public static List<String> filter(Set<String> options) {
        List<String> validOptions = new Vector<String>();
        for (String option : options){
            if (isValid(option))
                validOptions.add(option);
        }
        return validOptions;
    }

    public static boolean isValid(String option) {
        return !invalidOptions.contains(option);
    }
}
