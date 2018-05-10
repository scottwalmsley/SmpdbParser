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
 * The SmpdbPathway class comtains a storage interface and methods for the SMPDB
 * pathways. *
 *
 * @author Scott J. Walmsley
 * @version 0.0.1
 * @since 2018-04-07
 *
 */
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
