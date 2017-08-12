package com.helospark.tomcatmanagerhoneypot.text.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TextDeployUriParameters {
    @NotNull
    @Size(min = 1, max = 200)
    private String path;
    @Size(max = 200)
    private String tag = "";
    @Size(max = 200)
    private String war;
    private boolean update = false;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getWar() {
        return war;
    }

    public void setWar(String war) {
        this.war = war;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return "TextDeployUriParameters [path=" + path + ", tag=" + tag + ", war=" + war + ", update=" + update + "]";
    }

}
