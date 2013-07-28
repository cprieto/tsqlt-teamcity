package org.tsqlt.runner.agent;

class OptionValue {
    private String option = null;
    private String value = null;

    public OptionValue(String input){
        if (input == null || input.isEmpty())
            return;

        String[] parsed = input.trim().split("=");
        if (parsed.length != 2)
            return;

        option = parsed[0].trim();
        value = parsed[1].trim();
    }

    String getOption() {
        return option;
    }

    String getValue() {
        return value;
    }

    boolean isValid(){
        return (option != null && !option.isEmpty()) && (value != null && !value.isEmpty());
    }
}
