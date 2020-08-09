package com.roadmap.controller;

import com.roadmap.roadfind.RoadFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class That provides Services for city roads connectivity
 */
@RestController
public class RoadMapController {

    @Autowired
    RoadFinderService roadFinderService;

    /**
     * Service that check if there is road connectivity
     * between the give source and destination cities
     * @param source - City from where we need to Find the route
     * @param destination - City to which we need to find the route
     * @return String - Yes if connected
     */
    @GetMapping("/connected" )
    public String checkCitiesConnected(
            @RequestParam("origin") String source,
            @RequestParam("destination") String destination){
        return roadFinderService.isCitiesConnected(source, destination);
    }
}
