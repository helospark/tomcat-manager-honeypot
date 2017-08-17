package com.helospark.tomcatmanagerhoneypot.it.badips;

import static com.github.tomakehurst.wiremock.client.WireMock.anyRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.helospark.tomcatmanagerhoneypot.service.IpAddressProvider;
import com.helospark.tomcatmanagerhoneypot.service.UploadApplicationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = { "honeypot.badips.apikey=dummyApikey",
        "honeypot.badips.enabled=true",
        "honeypot.badips.setkey.uri=http://localhost:${wiremock.server.port}/set/key/{key}",
        "honeypot.badips.uri=http://localhost:${wiremock.server.port}/add/apache/{ip}" })
public class BadIpsReporterIT {
    @MockBean
    private IpAddressProvider ipAddressProvider;
    @Autowired
    private UploadApplicationService uploadApplicationService;

    @Before
    public void setUp() {
        when(ipAddressProvider.provideIpAddress()).thenReturn("8.8.8.8");
    }

    @Test
    public void testShouldReportToBadIpsWhenUploadApplicationServiceIsCalled() {
        // GIVEN

        // WHEN
        uploadApplicationService.uploadApplication("/", new byte[0]);

        // THEN
        verify(anyRequestedFor(urlEqualTo("/set/key/dummyApikey")));
        verify(anyRequestedFor(urlEqualTo("/add/apache/8.8.8.8")));
    }
}
