package com.roadmap.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Utility that loads city.txt information in to a collection object
 * this Utility class initializes once per session and used for all requests
 */
public class RoadMapData {
    //initialize static object so that it can be used by all the service request in a session
    public static RoadMapData DATA = new RoadMapData("city.txt");
    private Map<String, Set<String>> connectedCities = new HashMap<>();
    public RoadMapData(String dataFile) {
        super();
        this.initFromFile(dataFile);

    }
    /** Returns a singleton instance of this Database. */
    public static final RoadMapData getInstance() { return DATA; }

    /**
     * Method that loads city connected information in to a collection object
     * @param filename - where the city connection details are mentioned
     */
    private void initFromFile( String filename ) {
        if ( filename != null ) {
            try ( BufferedReader r = new BufferedReader(
                    new FileReader(getFileFromResources(filename))) ) {
                String citiesConStr;
                //Read all lines individually
                while( (citiesConStr = r.readLine() ) != null ) {
                    //Add city source and destination informationo
                    if ( citiesConStr.trim().length() > 0 ) {
                        addRoute(citiesConStr);
                    }
                }
            }
            catch(Throwable t) {
                t.printStackTrace(System.err);
            }
        }
    }

    /**
     * Routine that add city
     * @param citiesConStr
     */
    private void addRoute(String citiesConStr) {
        if(citiesConStr != null){
            String[] cityMap = citiesConStr.split(",");
            if(cityMap != null && cityMap.length > 1){
            String source = cityMap[0].trim().toUpperCase();
            String dest = cityMap[1].trim().toUpperCase();
            if(cityMap != null && cityMap.length > 1) {
                Set<String> destCities = connectedCities.get(source);
                Set<String> sourceCities = connectedCities.get(dest);
                if (destCities == null || destCities.isEmpty()) {
                    destCities = new HashSet<>();
                }
                if (sourceCities == null || sourceCities.isEmpty()) {
                    sourceCities = new HashSet<>();
                }
                destCities.add(dest);
                sourceCities.add(source);

                connectedCities.put(source, destCities);
                connectedCities.put(dest, sourceCities);
            }
            }
        }

    }

    /**
     * Load Resource File
     * @param filename - Resource file name which contains city connection information
     * @return File - which has source destination connectivity information
     * @throws FileNotFoundException - throws when file is not present in src/main/resources/static folder
     */
    private File getFileFromResources( String filename ) throws FileNotFoundException {
        File file = new File("./src/main/resources/static/"+filename);

        return file;
    }

    /**
     * gets connected cities
     * @return Map<String, Set<String>> - list of cities connected to source -> List of Destinations
     */
    public Map<String, Set<String>> getConnectedCities() {
        return connectedCities;
    }
}
