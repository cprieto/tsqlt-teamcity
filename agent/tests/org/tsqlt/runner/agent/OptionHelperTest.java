package org.tsqlt.runner.agent;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.AssertJUnit.assertFalse;

public class OptionHelperTest {

    @Test
    public void testItCanReturnListWithOnlyValidOptions() {
        Set<String> options = new HashSet<String>() {{
            add("option1");
            add("option2");
            add("domain"); // Invalid option
        }};

        List<String> result = OptionHelper.filter(options);

        assertFalse(result.contains("domain"));
    }
}

