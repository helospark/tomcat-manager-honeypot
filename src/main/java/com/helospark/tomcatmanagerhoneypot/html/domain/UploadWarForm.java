package com.helospark.tomcatmanagerhoneypot.html.domain;

import org.springframework.web.multipart.MultipartFile;

import com.helospark.tomcatmanagerhoneypot.html.validator.NotEmptyMultipartFile;

public class UploadWarForm {
    @NotEmptyMultipartFile
    private MultipartFile deployWar;

    public MultipartFile getDeployWar() {
        return deployWar;
    }

    public void setDeployWar(MultipartFile deployWar) {
        this.deployWar = deployWar;
    }

}
