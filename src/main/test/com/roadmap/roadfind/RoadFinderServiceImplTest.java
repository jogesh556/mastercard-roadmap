package com.roadmap.roadfind;

import junit.framework.TestCase;
import org.junit.Test;

public class RoadFinderServiceImplTest extends TestCase {

    RoadFinderService roadFinderService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        roadFinderService = new RoadFinderServiceImpl();
    }

    @Test
    public void testIsCitiesConnectedWhenThereIsCommonRoad() {
        //Boston&destination=Newark
        String isConnected =
                roadFinderService.isCitiesConnected("Boston", "Newark");
        assertEquals("Failed to find Connected Cities",  "Yes", isConnected);
    }

    @Test
    public void testIsCitiesConnectedWhenNoStops() {
        //Boston&destination=Newark
        String isConnected =
                roadFinderService.isCitiesConnected("Trenton", "Albany");
        assertEquals("Failed to find Connected Cities",  "Yes", isConnected);
    }

    @Test
    public void testIsCitiesConnectedNoRoad() {
        //Boston&destination=Newark
        String isConnected =
                roadFinderService.isCitiesConnected("Philadelphia", "Albany");
        assertEquals("Failed to find Dis-Connected Cities",  "No", isConnected);
    }

}