package com.helospark.tomcatmanagerhoneypot.text;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.RestErrorHandler;
import com.helospark.tomcatmanagerhoneypot.service.UploadApplicationService;
import com.helospark.tomcatmanagerhoneypot.text.domain.TextDeployUriParameters;

@RestController
@RestErrorHandler
public class DeployController {
    private UploadApplicationService deployApplicationService;

    public DeployController(UploadApplicationService deployApplicationService) {
        this.deployApplicationService = deployApplicationService;
    }

    @PutMapping(path = "/text/deploy")
    public String handle(@Valid TextDeployUriParameters textDeployUriParameters,
            @RequestBody @NotNull byte[] body) {
        return deployApplicationService.uploadApplication(textDeployUriParameters.getPath(), body);
    }
}
