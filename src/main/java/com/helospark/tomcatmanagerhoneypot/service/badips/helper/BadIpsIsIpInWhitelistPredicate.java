package com.helospark.tomcatmanagerhoneypot.service.badips.helper;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Component;

@Component
public class BadIpsIsIpInWhitelistPredicate implements Predicate<String> {
    private List<String> whitelistedIps;

    public BadIpsIsIpInWhitelistPredicate(@Value("${honeypot.badips.whitelist}") String[] whitelistedId) {
        this.whitelistedIps = Arrays.asList(whitelistedId);
    }

    @Override
    public boolean test(String ipToCheck) {
        return whitelistedIps.stream()
                .map(whiteListEntry -> new IpAddressMatcher(whiteListEntry))
                .filter(matcher -> matcher.matches(ipToCheck))
                .findFirst()
                .isPresent();
    }

}
