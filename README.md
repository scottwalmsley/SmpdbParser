# SmpdbParser
> *Copyright 2018 [Scott Walmsley](https://scottwalmsley.github.io)*
<br>A parser for the Small Molecules Pathways DataBase (SMPDB).

## Background
The SMPDB (http://smpdb.ca) is a database of pathway and small molecule related information.
This Java library was developed to facilitate extraction of key bits of
information from the SMPDB and to use that information to build a
local MongoDB and Neo4J storage databases. SmpdbParser is part of a larger
project, Metabolic Network Amalgamation, and the SMPDB
information is used to build graphical networks of metabolic compounds.

### *Usage*
The SmpdbParser is meant to be used as a .jar library.

### *Origin*
SmpdbParser is provided free of charge for academic use.

### *License*
Apache 2.0

### *Requirements*
###### *Java version 8 update 161*
SmpdbParser needs the metabolites.csv, the pathways.csv, and the pathways
files in BioPax xml format.  These are all downloadable from the
http://smpdb.ca/downloads webpage.


## Components
### *1. SmpdbParser*
This class contains Main for example usage of reading the SMPDB pathways
and metabolite to pathway relationships.  These functions to read the SMPDB
 files are intensive and take a long time. To shorten subsequent read times,
  the data is serialized for later deserialization in other functions.

### *2. SmpdbFile*
This class is the primary storage - object class for information that has
 been parsed from the SMPDB files.

### *3. SmpdbPathway*
The object class for the Smpdb pathways as seen in the pathways.csv file.

### *4. SmpdbMetabolite*
The object class for the Smpdb Metabolite-pathway relationships as seen in
the metabolites.csv file.

### *5. SmpdbMap and SmpdbMetaboliteMap*
These are files containing hash maps of indexed pathways and metabolite-pathays
 relationships.   It is these classes which are serialized for later usage with
 MongoDB (a document database) and Neo4J (a graph database).


## Specific usage examples

#### *General Usage:*
```java
 // Instantiate a new SmpdbFile reader and storage class.
 SmpdbFile sf = new SmpdbFile();

 // Set the location of the directory containing the smpdb biopax .owl files.
 sf.setDirBioPax("smpdb_biopax");

 // Read the biopax owl files.
 sf.readPaxFiles();

 // Set the indexes for later serialization.
 sf.setSmpdMap();

 // Read the pathways.csv file and store the SMPDB pathway information.
 sf.readPathwayFile("pathways.csv", ",");

 // Read the metabolites.csv file and store the metabolite-pathways relationship information.
 sf.readMetabolitesFile("metabolites.csv");

 // Extract the unique list of metabolites that were parsed from the relationships.
 sf.setUniqueSmpdMetabolites();

 //  Set the relationship indexes for later serialization.
 sf.setMetaboliteMap();
```


### *Serialization*
The MetaboliteMap and the SmpdbMap classes can be serialized through the
parent SmpdbFile class. This creates a serialized bytecode object.   Within
these two classes are important serialization UIDs:
```java
 private static final long serialVersionUID = 1L;
 ```
..it is important that these are appropriately used in the context of your
usage or a NoClassDef exception will occur, either on the same machine or
between machines.  They have been set up appropriately in this repository
and work as written.   Examples of serialization and de-serialization are
as follows:

##### *Serialization*
```java
 // Serialize the SmpdbMap class.
 sf.serializeSmpdMap("smpdMap.ser");

 // Serialize the MetaboliteMap class.
 sf.serializeMetaboliteMap("metabolitesMap.ser");
```

##### *Deserialization*
```java
 // Instantiate a new SmpdbFile class:
 SmpdbFile sf = new SmpdbFile

 // Deserialization of a SmpdbMap into the SmpdbFile storage class
 sf.setSmpdMap((SmpdbMap) sf.deSerializeSmpdData("smpdMap.ser"));

 // Deserialization of a MetaboliteMap into the SmpdbFile storage class
 sf.setMetaboliteMap( (SmpdbMetaboliteMap) sf.deSerializeSmpdData("metabolitesMap.ser"));


 // Deserialization directly into a SmpdbMap class
 SmpdbMap sm = (SmpdbMap) sf.deSerializeSmpdData("smpdMap.ser"));

 // Deserialization directly into a MetaboliteMap class
 MetaboliteMap mm = (MetaboliteMap) sf.deSerializeSmpdData("smpdMap.ser"));
```




