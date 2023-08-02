package com.qa.spring.hedgehog.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.spring.hedgehog.domain.Hedgehog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // loads the application context
@AutoConfigureMockMvc // create a MockMVC bean
public class HedgehogMvcTest {


    @Autowired // tells spring to inject the MockMVC bean into this class
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    void testCreate() throws Exception {
        // METHOD: POST
        // PATH: /hedgehog/create
        // BODY: JSON
        // HEADERS: CONTENT-TYPE: application\json
        Hedgehog sonic = new Hedgehog("Sonic", "blue", 15);
        System.out.println("DATA: " + sonic);
        String sonicJSON = this.mapper.writeValueAsString(sonic);
        System.out.println("JSON: " + sonicJSON);
        RequestBuilder req = MockMvcRequestBuilders.post("/hedgehog/create").content(sonicJSON).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
        Hedgehog responseBody = new Hedgehog(1, "Sonic", "blue", 15);
        System.out.println("DATA: " + responseBody);
        String responseBodyJSON = this.mapper.writeValueAsString(responseBody);
        System.out.println("JSON: " + responseBodyJSON);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(responseBodyJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testCreate2() throws Exception {
        String sonicJSON = this.mapper.writeValueAsString(new Hedgehog("Sonic", "blue", 15));
        String responseBodyJSON = this.mapper.writeValueAsString(new Hedgehog(1, "Sonic", "blue", 15));
        this.mvc.perform(
                MockMvcRequestBuilders.
                        post("/hedgehog/create")
                        .content(sonicJSON)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(responseBodyJSON));
    }
}
