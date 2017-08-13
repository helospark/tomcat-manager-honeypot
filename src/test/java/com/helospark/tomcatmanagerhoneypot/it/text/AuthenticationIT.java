package com.helospark.tomcatmanagerhoneypot.it.text;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

@RunWith(Parameterized.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthenticationIT extends AbstractTextControllerIT {
    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    private String uri;
    private HttpMethod method;

    public AuthenticationIT(String uri, HttpMethod method) {
        this.uri = uri;
        this.method = method;
    }

    @Test
    public void testGetResources() {
        // GIVEN

        // WHEN
        ResponseEntity<String> result = performNonauthenticatedCall(uri, method);

        // THEN
        assertThat(result.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }

    @Parameters
    public static Object[][] dataProviderAdd() {
        return new Object[][] {
                { "resources?type=database", HttpMethod.GET },
                { "start?path=asd", HttpMethod.GET },
                { "stop?path=asd", HttpMethod.GET },
                { "undeploy?path=asd", HttpMethod.GET },
                { "findleaks", HttpMethod.GET },
                { "list", HttpMethod.GET },
                { "reload", HttpMethod.GET },
        };
    }

}
