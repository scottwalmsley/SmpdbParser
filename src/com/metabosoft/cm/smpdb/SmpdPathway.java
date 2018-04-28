package com.metabosoft.cm.smpdb;

import java.io.Serializable;

public class SmpdPathway implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String subject;
    private String pwID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPwID() {
        return pwID;
    }

    public void setPwID(String pwID) {
        this.pwID = pwID;
    }
}
