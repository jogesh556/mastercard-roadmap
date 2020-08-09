package com.roadmap.roadfind;

import org.springframework.stereotype.Component;

@Component
public interface RoadFinderService {


    /**
     * Service that check if there is road connectivity
     * between the give source and destination cities
     * @param source - City from where we need to Find the route
     * @param destination - City to which we need to find the route
     * @return String - Yes if connected
     */
    String isCitiesConnected(String source, String destination);
}
