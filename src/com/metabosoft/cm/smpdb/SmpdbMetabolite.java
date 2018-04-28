package com.metabosoft.cm.smpdb;

import java.io.Serializable;

public class SmpdbMetabolite implements Serializable {
    private static final long serialVersionUID = 1L;
    private String metabolite;
    private String metabolite_id;
    private String name;
    private String hmid;
    private String keggid;
    private String pathwayID;
    private String pwID;
    private String cas;
    private String formula;
    private String type;
    private String smiles;

    public String getMetabolite() {
        return metabolite;
    }

    public void setMetabolite(String metabolite) {
        this.metabolite = metabolite;
    }

    public String getMetabolite_id() {
        return metabolite_id;
    }

    public void setMetabolite_id(String metabolite_id) {
        this.metabolite_id = metabolite_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHmid() {
        return hmid;
    }

    public void setHmid(String hmid) {
        this.hmid = hmid;
    }

    public String getKeggid() {
        return keggid;
    }

    public void setKeggid(String keggid) {
        this.keggid = keggid;
    }

    public String getPathwayID() {
        return pathwayID;
    }

    public void setPathwayID(String pathwayID) {
        this.pathwayID = pathwayID;
    }

    public String getPwID() {
        return pwID;
    }

    public void setPwID(String pwID) {
        this.pwID = pwID;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSmiles() {
        return smiles;
    }

    public void setSmiles(String smiles) {
        this.smiles = smiles;
    }
}
