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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The SmpdbMap class is a storage class for serializing the smpdb information.
 *
 * @author Scott J. Walmsley
 * @version 0.0.1
 * @since 2018-04-07
 *
 */
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
