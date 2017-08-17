package com.helospark.tomcatmanagerhoneypot.html.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ExpireSessionUrlParameters {
    @Min(0)
    private int idle;
    @NotNull
    @NotEmpty
    private String path;

    public int getIdle() {
        return idle;
    }

    public void setIdle(int idle) {
        this.idle = idle;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
