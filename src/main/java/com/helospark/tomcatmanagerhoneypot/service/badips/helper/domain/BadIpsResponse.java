package com.helospark.tomcatmanagerhoneypot.service.badips.helper.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BadIpsResponse {
    @JsonProperty("err")
    private String err;
    @JsonProperty("suc")
    private String suc;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getSuc() {
        return suc;
    }

    public void setSuc(String suc) {
        this.suc = suc;
    }

    @Override
    public String toString() {
        return "BadIpsResponse [err=" + err + ", suc=" + suc + "]";
    }

}
