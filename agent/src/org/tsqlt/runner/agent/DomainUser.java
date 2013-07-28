package org.tsqlt.runner.agent;

import org.jetbrains.annotations.NotNull;

public class DomainUser {
    private final String user;
    private final String domain;

    public DomainUser(@NotNull String input){
        if (input.contains("\\")){
            String[] parsed = input.split("\\\\", 2);
            user = parsed[1];
            domain = parsed[0];
        } else {
            user = input;
            domain = null;
        }
    }

    public String getUser(){
        return user;
    }

    public String getDomain(){
        return domain;
    }

    public boolean hasDomain(){
        return domain != null && !domain.isEmpty();
    }

    @Override
    public String toString() {
        return hasDomain() ? String.format("%s\\%s", domain, user) : user;
    }
}
