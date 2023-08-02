package com.qa.spring.hedgehog.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.spring.hedgehog.domain.Hedgehog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // loads the application context
@AutoConfigureMockMvc // create a MockMVC bean
@Sql(scripts = {"classpath:hedgehog-schema.sql", "classpath:hedgehog-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
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
        RequestBuilder req = post("/hedgehog/create").content(sonicJSON).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = status().isCreated();
        Hedgehog responseBody = new Hedgehog(2, "Sonic", "blue", 15);
        System.out.println("DATA: " + responseBody);
        String responseBodyJSON = this.mapper.writeValueAsString(responseBody);
        System.out.println("JSON: " + responseBodyJSON);
        ResultMatcher checkBody = content().json(responseBodyJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testCreate2() throws Exception {
        String sonicJSON = this.mapper.writeValueAsString(new Hedgehog("Sonic", "blue", 15));
        String responseBodyJSON = this.mapper.writeValueAsString(new Hedgehog(2, "Sonic", "blue", 15));
        this.mvc.perform(
                        post("/hedgehog/create")
                                .content(sonicJSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(responseBodyJSON));
    }

    @Test
    void testCreateMultiple(){}

    @Test
    void testRead() throws Exception {
        final int id = 1;
        String responseBody = this.mapper.writeValueAsString(new Hedgehog(id, "Silver", "silver", 12));
        this.mvc.perform(get("/hedgehog/get/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void testReadAll() {}

    @Test
    void testReadName() {};
    @Test
    void testUpdate() {}

    @Test
    void testDelete() {}

    // Nice to have but not required:

    @Test // try and GET an object that doesn't exist
    void testNotFound(){}

    @Test // try and create an object that doesn't pass validation and check that the error works
    void testFailCreate() {};

}
