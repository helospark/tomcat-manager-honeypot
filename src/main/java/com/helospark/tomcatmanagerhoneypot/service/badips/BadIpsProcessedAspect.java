package com.helospark.tomcatmanagerhoneypot.service.badips;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.helospark.tomcatmanagerhoneypot.service.IpAddressProvider;

@Component
@Aspect
public class BadIpsProcessedAspect {
    private BadIpsReporterService badIpsReporterService;
    private IpAddressProvider ipAddressProvider;

    public BadIpsProcessedAspect(BadIpsReporterService badIpsReporterService, IpAddressProvider ipAddressProvider) {
        this.badIpsReporterService = badIpsReporterService;
        this.ipAddressProvider = ipAddressProvider;
    }

    @After("@annotation(com.helospark.tomcatmanagerhoneypot.service.badips.BadIpsReported)")
    public void processBadIpsReporting() {
        String currentIpAddress = ipAddressProvider.provideIpAddress();
        badIpsReporterService.report(currentIpAddress);
    }
}
