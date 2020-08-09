package com.roadmap.dao;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Map;
import java.util.Set;


public class RoadMapDataTest extends TestCase {

    @Test
   public void testGetConnectedCities() {
        RoadMapData roadMapData = RoadMapData.getInstance();
        Map<String, Set<String>> cities = roadMapData.getConnectedCities();
        assertNotNull("Could not able to Load City List",cities);
        assertTrue("Not able to load All City List",cities.size() > 0);

    }
}