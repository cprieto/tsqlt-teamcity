package org.tsqlt.runner.agent;

import java.util.HashMap;
import java.util.Map;

public class OptionParser {

    private final Map<String, String> options = new HashMap<String, String>();

    public OptionParser(String input){
        String[] optionList = input.split(";");
        for(String key : optionList) {
            OptionValue option = new OptionValue(key);
            if (!option.isValid()) break;

            options.put(option.getOption(), option.getValue());
        }
    }

    public Map<String, String> getOptions(){
        return options;
    }
}
