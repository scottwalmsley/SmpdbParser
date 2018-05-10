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

import java.io.IOException;

/**
 * The SmpdbParser class comtains the Main and general examples for the entrypoint to
 * parsing the SMPDB database.
 *
 * @author Scott J. Walmsley
 * @version 0.0.1
 * @since 2018-04-07
 *
 */
public class SmpdbParser  {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        boolean load = false;


        SmpdbFile sf = new SmpdbFile();

        if(load) {

            System.out.println("Indexing Bioxpax for taxonomies to SMPDB mappings");

            sf.setDirBioPax("smpdb_biopax");
            sf.readPaxFiles();
            sf.setSmpdMap();
            sf.serializeSmpdMap("smpdMap.ser");
        }else {
            sf.setSmpdMap((SmpdbMap) sf.deSerializeSmpdData("smpdMap.ser"));
        }

        System.out.println("Loading SMPDB pathways.");
        System.out.println("Loading SMPDB metabolite - pathway relationships.");

        if(load) {

            sf.readPathwayFile("pathways.csv", ",");
            sf.readMetabolitesFile("metabolites.csv");
            sf.setUniqueSmpdMetabolites();

            sf.setMetaboliteMap();
            sf.serializeMetaboliteMap("metabolitesMap.ser");
            sf.serialize("smpdb.ser");

        }else{

            sf.setMetaboliteMap( (SmpdbMetaboliteMap) sf.deSerializeSmpdData("metabolitesMap.ser"));
            SmpdbFile sf2  = (SmpdbFile) sf.deSerializeSmpdData("smpdb.ser");


        }




    }
}


