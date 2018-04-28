package com.metabosoft.cm.smpdb;

import org.biopax.paxtools.impl.level3.PathwayImpl;
import org.biopax.paxtools.io.BioPAXIOHandler;
import org.biopax.paxtools.io.SimpleIOHandler;
import org.biopax.paxtools.model.Model;
import org.biopax.paxtools.model.level3.BioSource;
import org.biopax.paxtools.model.level3.Pathway;
import org.biopax.paxtools.model.level3.UnificationXref;
import org.biopax.paxtools.model.level3.Xref;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SmpdbFile {


    private File dirBioPax;
    private Map<String,String> pwToSMDB = new HashMap<>();
    private String[] bioPaxFiles;
    private Map<String,Integer> pwToTaxonomy = new HashMap<>();
    private Map<Integer,String> taxonomyToName = new HashMap<>();
    private SmpdbMap smpdMap = new SmpdbMap();
    private Map<String, String> smpdToPw = new HashMap<>();
    private Map<String, SmpdPathway> pathways = new TreeMap<>();
    private List<SmpdbMetabolite> metaboliteRelationships = new ArrayList<>();
    private Map<String,SmpdbMetabolite> uniqueMetaboliteList = new HashMap<>();
    private SmpdbMetaboliteMap metaboliteMap = new SmpdbMetaboliteMap();


    public void setDirBioPax(String path) {
        this.dirBioPax = new File(path);
        this.bioPaxFiles = this.dirBioPax.list();
    }

    public void readPaxFiles() {

        String[] split;
        String pwAccesssion;
        Map<String, String> paxSearch;
        UnificationXref uxr;
        String taxonomy;


        for(String bioPaxFile:bioPaxFiles){
            String n = "";
            split = bioPaxFile.split("\\\\");
            pwAccesssion = split[split.length-1];
            pwAccesssion = pwAccesssion.replace(".owl","");

            // Get the biopax model to retrieve the taxonomy
            bioPaxFile = "smpdb_biopax\\" + bioPaxFile;
            FileInputStream fin = null;
            try {
                fin = new FileInputStream(bioPaxFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            BioPAXIOHandler handler = new SimpleIOHandler();
            Model model = handler.convertFromOWL(fin);

            Set<UnificationXref> pax = model.getObjects(UnificationXref.class);
            Set<Pathway> paxPath = model.getObjects(Pathway.class);

            Iterator<Pathway> pxPathit = paxPath.iterator();

            // Build the index maps
            while(pxPathit.hasNext()){
                PathwayImpl pe = (PathwayImpl) pxPathit.next();

                BioSource o = pe.getOrganism();
                if( o !=null){
                    Iterator <Xref> xr = pe.getXref().iterator();
                    String a = xr.next().getId();
                    String b = xr.next().getId();
                    String pw;
                    String smp;

                    if(a.startsWith("PW")){
                        pw = a;
                        smp = b;
                    }else{
                        pw = b;
                        smp = a;
                    }
                    n = o.getStandardName();


                    this.pwToSMDB.put(pw,smp);
                    this.smpdToPw.put(smp,pw);
                }

            }


            //getPaxTaxonomy();

            Iterator<UnificationXref> it = pax.iterator();
            //Load the bioPax data.
            paxSearch = new HashMap();
            while (it.hasNext()) {
                uxr = it.next();
                paxSearch.put(uxr.getDb(), uxr.getId());

            }
            taxonomy = paxSearch.get("Taxonomy");

            this.pwToTaxonomy.put(pwAccesssion, Integer.valueOf(taxonomy));
            this.taxonomyToName.put(Integer.valueOf(taxonomy),n);


            if(taxonomy.contentEquals("2")){
                System.out.println(bioPaxFile);
            }


        }


    }

    public void setSmpdMap() {
        this.smpdMap = smpdMap;
        this.smpdMap.setPwToSMDB(this.pwToSMDB);
        this.smpdMap.setPwToTaxonomy(this.pwToTaxonomy);
        this.smpdMap.setTaxonomyToName(this.taxonomyToName);
        this.smpdMap.setSmpToPW();
    }

    public void setSmpdMap(SmpdbMap map){
        this.smpdMap = map;
    }

    public final void serializeSmpdMap(String fh) throws IOException {
        FileOutputStream fileOut =  new FileOutputStream(fh);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this.smpdMap);
        out.close();
        fileOut.close();
    }

    public final Object deSerializeSmpdData(String fh) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fh);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Object data =  in.readObject();
        in.close();
        fileIn.close();
        return data;
    }

    public void readPathwayFile (String fh, String delimiter){

        BufferedReader br = null;
        SmpdPathway smpdPathway;

        String line;

        try {

            br = new BufferedReader(new FileReader(fh));
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                if (line.startsWith("SMP")) {
                    smpdPathway = new SmpdPathway();
                    String[] smpdEntry = line.split(delimiter);
                    smpdPathway.setId(smpdEntry[0]);
                    smpdPathway.setName(smpdEntry[1]);
                    smpdPathway.setSubject(smpdEntry[2]);
                    smpdPathway.setPwID(this.smpdMap.getSmpToPW().get(smpdEntry[0]));
                    this.pathways.put(smpdEntry[0],smpdPathway);

                }


            }
        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void readMetabolitesFile (String fh){

        BufferedReader br = null;
        SmpdbMetabolite smpdMetabolite;
        String line;
        String[] smpdEntry;


        try {


            br = new BufferedReader(new FileReader(fh));
            br.readLine();


            while ((line = br.readLine()) != null) {


                // use comma as separator
                if (line.startsWith("SMP")) {
                    //System.out.println(line);
                    smpdMetabolite = new SmpdbMetabolite();

                    //String[] smpdEntry = line.split(delimiter);
                    smpdEntry = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                    if(smpdEntry.length>0 && smpdEntry[0] !=null) {
                        smpdMetabolite.setPathwayID(smpdEntry[0]);
                        if(this.smpdMap.getSmpToPW() !=null) {
                            smpdMetabolite.setPwID(this.smpdMap.getSmpToPW().get(smpdEntry[0]));
                        }
                    }

                    if(smpdEntry.length>1 && smpdEntry[1] !=null)
                        smpdMetabolite.setMetabolite(smpdEntry[1]);

                    if(smpdEntry.length>2 && smpdEntry[2] !=null)
                        smpdMetabolite.setType(smpdEntry[2]);

                    if(smpdEntry.length>3 && smpdEntry[3] !=null)
                        smpdMetabolite.setMetabolite_id(smpdEntry[3]);

                    if(smpdEntry.length>4 && smpdEntry[4] !=null)
                        smpdMetabolite.setName(smpdEntry[4]);

                    if(smpdEntry.length>5 && smpdEntry[5] !=null)
                        smpdMetabolite.setHmid(smpdEntry[5]);

                    if(smpdEntry.length>6 && smpdEntry[6] !=null)
                        smpdMetabolite.setKeggid(smpdEntry[6]);

                    if(smpdEntry.length>9 && smpdEntry[9] !=null)
                        smpdMetabolite.setCas(smpdEntry[9]);

                    if(smpdEntry.length>10 && smpdEntry[10] !=null)
                        smpdMetabolite.setFormula(smpdEntry[10]);

                    if(smpdEntry.length>12 && smpdEntry[12] !=null)
                        smpdMetabolite.setSmiles(smpdEntry[12]);




                    this.metaboliteRelationships.add(smpdMetabolite);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setUniqueSmpdMetabolites(){

        SmpdbMetabolite uniquemetabolite;

        Iterator<SmpdbMetabolite> it  = this.metaboliteRelationships.iterator();

        while(it.hasNext()){
            uniquemetabolite = it.next();
            uniqueMetaboliteList.put(uniquemetabolite.getMetabolite_id(), uniquemetabolite);
        }

        // --> likely to remove!!!!
        SmpdbMetaboliteMap metMap = new SmpdbMetaboliteMap();
        metMap.setMetaboliteRelationshipList(this.metaboliteRelationships);
        metMap.setPathwayList(this.pathways);
        metMap.setUniqueMetaboliteList(uniqueMetaboliteList);

    }

    public void setMetaboliteMap(){
        this.metaboliteMap.setUniqueMetaboliteList(this.uniqueMetaboliteList);
        this.metaboliteMap.setPathwayList(this.pathways);
        this.metaboliteMap.setMetaboliteRelationshipList(this.metaboliteRelationships);
    }

    public void setMetaboliteMap(SmpdbMetaboliteMap map){
        this.metaboliteMap = map;
    }

    public final void serializeMetaboliteMap(String fh) throws IOException {
        FileOutputStream fileOut =  new FileOutputStream(fh);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this.metaboliteMap);
        out.close();
        fileOut.close();
    }

}
