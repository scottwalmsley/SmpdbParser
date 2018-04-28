package com.metabosoft.cm.smpdb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SmpdbMetaboliteMap implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<SmpdbMetabolite> metaboliteRelationshipList;
    private Map<String, SmpdPathway> pathwayList;
    private Map<String, SmpdbMetabolite> uniqueMetaboliteList;

    public List<SmpdbMetabolite> getMetaboliteRelationshipList() {
        return this.metaboliteRelationshipList;
    }

    public void setMetaboliteRelationshipList(List<SmpdbMetabolite> metaboliteRelationshipList) {
        this.metaboliteRelationshipList = metaboliteRelationshipList;
    }

    public Map<String, SmpdPathway> getPathwayList() {
        return this.pathwayList;
    }

    public void setPathwayList(Map<String, SmpdPathway> pathwayList) {
        this.pathwayList = pathwayList;
    }

    public Map<String, SmpdbMetabolite> getUniqueMetaboliteList() {
        return this.uniqueMetaboliteList;
    }

    public void setUniqueMetaboliteList(Map<String, SmpdbMetabolite> uniqueMetaboliteList) {
        this.uniqueMetaboliteList = uniqueMetaboliteList;
    }

}
