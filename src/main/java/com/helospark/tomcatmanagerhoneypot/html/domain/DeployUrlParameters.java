package com.helospark.tomcatmanagerhoneypot.html.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class DeployUrlParameters {
    @NotNull
    @NotEmpty
    private String deployWar;
    @NotNull
    @NotEmpty
    private String deployPath;
    @NotNull
    @NotEmpty
    private String deployConfig;

    public String getDeployWar() {
        return deployWar;
    }

    public void setDeployWar(String deployWar) {
        this.deployWar = deployWar;
    }

    public String getDeployPath() {
        return deployPath;
    }

    public void setDeployPath(String deployPath) {
        this.deployPath = deployPath;
    }

    public String getDeployConfig() {
        return deployConfig;
    }

    public void setDeployConfig(String deployConfig) {
        this.deployConfig = deployConfig;
    }

}
