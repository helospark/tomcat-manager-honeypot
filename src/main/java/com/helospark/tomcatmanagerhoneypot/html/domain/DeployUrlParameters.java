package com.helospark.tomcatmanagerhoneypot.html.domain;

public class DeployUrlParameters {
    private String deployWar = "";
    private String deployPath = "";
    private String deployConfig = "";

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
