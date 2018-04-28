package com.metabosoft.cm.smpdb;

import java.io.IOException;


public class SmpdbParser  {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        boolean load = true;


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
        }else{
            sf.setMetaboliteMap( (SmpdbMetaboliteMap) sf.deSerializeSmpdData("metabolitesMap.ser"));
        }

        //int d = 0;

        //////////////////////////////////////////////////////////

    }
}


