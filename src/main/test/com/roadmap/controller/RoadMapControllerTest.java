package com.roadmap.controller;

import com.roadmap.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RoadMapControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    public void testCheckCitiesConnected() throws Exception{
        mock.perform(get("/connected?origin=Boston&destination=Newark")).
                andExpect(status().isOk())
       .andExpect(jsonPath("$", is("Yes")));
    }

    @Test
    public void testCheckCitiesConnectedWhenNotConnected() throws Exception{
        mock.perform(get("/connected?origin=Boston&destination=Philadelphia")).
                andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Yes")));
    }

    @Test
    public void testCheckCitiesConnectedWhenCommonRoute() throws Exception{
        mock.perform(get("/connected?origin=Philadelphia&destination=Albany")).
                andExpect(status().isOk())
                .andExpect(jsonPath("$", is("No")));
    }
}