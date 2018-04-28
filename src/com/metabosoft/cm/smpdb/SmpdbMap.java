package com.metabosoft.cm.smpdb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SmpdbMap implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String,String> pwToSMDB = new HashMap<>();
    private Map<String,String> smpToPW = new HashMap<>();
    private Map<String,Integer> pwToTaxonomy = new HashMap<>();
    private Map<Integer,String> taxonomyToName = new HashMap<>();


    public Map<String, String> getPwToSMDB() {
        return pwToSMDB;
    }

    public void setPwToSMDB(Map<String, String> pwToSMDB) {
        this.pwToSMDB = pwToSMDB;
    }

    public Map<String, Integer> getPwToTaxonomy() {
        return pwToTaxonomy;
    }

    public void setPwToTaxonomy(Map<String, Integer> pwToTaxonomy) {
        this.pwToTaxonomy = pwToTaxonomy;
    }

    public Map<Integer, String> getTaxonomyToName() {
        return taxonomyToName;
    }

    public void setTaxonomyToName(Map<Integer, String> taxonomyToName) {
        this.taxonomyToName = taxonomyToName;
    }

    public Map<String, String> getSmpToPW() {
        return smpToPW;
    }

    public void setSmpToPW() {
        this.smpToPW = this.pwToSMDB.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
}
