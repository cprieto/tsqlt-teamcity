package org.tsqlt.runner.agent;

import jetbrains.buildServer.log.Loggers;
import org.jetbrains.annotations.NotNull;
import org.tsqlt.runner.common.PropertyNames;

import java.util.Map;

public class DomainUser {
    private String user;
    private String domain;
    private boolean useNtlm = false;

    public DomainUser(@NotNull String input) {
        if (input.contains("\\")) {
            String[] parsed = input.split("\\\\", 2);
            user = parsed[1];
            domain = parsed[0];
        } else {
            user = input;
            domain = null;
        }
    }

    public boolean getUseNtlm() {
        return useNtlm;
    }

    public void setUseNtlm(boolean useNtlm) {
        this.useNtlm = useNtlm;
        if (useNtlm) {
            user = null;
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

    public static DomainUser create(Map<String, String> properties){
        String domainAndUser = properties.containsKey(PropertyNames.USER_DOMAIN)
                ? properties.get(PropertyNames.USER_DOMAIN) : "";

        DomainUser domainUser = new DomainUser(domainAndUser);
        boolean useWinAuth = properties.containsKey(PropertyNames.WINDOWS_AUTH)
                ? Boolean.parseBoolean(properties.get(PropertyNames.WINDOWS_AUTH)) : false;
        if (useWinAuth)
            domainUser.setUseNtlm(useWinAuth);

        return domainUser;
    }
}
