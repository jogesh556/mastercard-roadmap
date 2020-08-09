package com.roadmap.roadfind;

import com.roadmap.dao.RoadMapData;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class RoadFinderServiceImpl implements RoadFinderService {
    private final String CONNECTED = "Yes";
    private final String NOT_CONNECTED = "No";

    /**
     * Implementation method for the Service that check if there is road connectivity
     * between the give source and destination cities
     * @param source - City from where we need to Find the route
     * @param destination - City to which we need to find the route
     * @return String - Yes if source and destinations are connected, No if not connected
     */
    @Override
    public String isCitiesConnected(String source, String destination) {
        String citiesConnected = NOT_CONNECTED;

        RoadMapData roadMapData = RoadMapData.getInstance();
        //Fetch all available connection data

        Map<String, Set<String>> cityMap = roadMapData.getConnectedCities();
        if(cityMap != null){
            //Get all the cities that are connected to given source
            Set<String> connectedList = cityMap.get(source.toUpperCase());
            if(connectedList != null){
                //check if the destination city is in the connected list of source
                citiesConnected = connectedList.contains(destination.toUpperCase()) ? CONNECTED : NOT_CONNECTED;
                //check If destination city is not in the connected cities list of source
                if(citiesConnected.equalsIgnoreCase(NOT_CONNECTED)){
                    //check all the connected cities list if the destination city is connected to any one of them
                    //if connected assign "Yes" otherwise assign "No"
                    citiesConnected = connectedList.stream().filter(
                            city -> (cityMap.get(city.toUpperCase()) != null) &&
                                    (cityMap.get(city.toUpperCase()).contains(destination.toUpperCase()))).count() > 0 ?
                            CONNECTED : NOT_CONNECTED;
                }
            }
        }
        //return final response if the source and destinations are connected
        return citiesConnected;
    }

}
