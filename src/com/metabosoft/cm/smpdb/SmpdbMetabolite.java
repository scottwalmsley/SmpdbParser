/*
 * Copyright 2018  Scott J. Walmsley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.metabosoft.cm.smpdb;
import java.io.Serializable;

/**
 * The SmpdbFMetabolite class is a storage class for accessing SMPDB.ca formatted metabolite
 * information.
 *
 * @author Scott J. Walmsley
 * @version 0.0.1
 * @since 2018-04-07
 *
 */
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
