package com.helospark.tomcatmanagerhoneypot.service.badips.helper;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BadIpsReportingEnabledPredicate implements Predicate<String> {
    private BadIpsIsIpInWhitelistPredicate badIpsIsIpInWhitelistPredicate;
    private boolean badIpsEnabled;

    public BadIpsReportingEnabledPredicate(BadIpsIsIpInWhitelistPredicate badIpsIsIpInWhitelistPredicate,
            @Value("${honeypot.badips.enabled}") boolean badIpsEnabled) {
        this.badIpsIsIpInWhitelistPredicate = badIpsIsIpInWhitelistPredicate;
        this.badIpsEnabled = badIpsEnabled;
    }

    @Override
    public boolean test(String ipAddress) {
        return badIpsEnabled && !badIpsIsIpInWhitelistPredicate.test(ipAddress);
    }

}
