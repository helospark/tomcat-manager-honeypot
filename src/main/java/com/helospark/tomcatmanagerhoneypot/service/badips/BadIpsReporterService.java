package com.helospark.tomcatmanagerhoneypot.service.badips;

import org.springframework.stereotype.Service;

import com.helospark.tomcatmanagerhoneypot.service.badips.helper.BadIpsReportingEnabledPredicate;
import com.helospark.tomcatmanagerhoneypot.service.badips.helper.BadIpsRestCaller;

@Service
public class BadIpsReporterService {
    private BadIpsRestCaller badIpsRestCaller;
    private BadIpsReportingEnabledPredicate badIpsReportingEnabledPredicate;

    public BadIpsReporterService(BadIpsRestCaller badIpsRestCaller,
            BadIpsReportingEnabledPredicate badIpsReportingEnabledPredicate) {
        this.badIpsRestCaller = badIpsRestCaller;
        this.badIpsReportingEnabledPredicate = badIpsReportingEnabledPredicate;
    }

    public void report(String ipAddress) {
        if (badIpsReportingEnabledPredicate.test(ipAddress)) {
            badIpsRestCaller.report(ipAddress);
        }
    }
}
