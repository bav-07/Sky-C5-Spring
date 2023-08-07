package com.qa.spring.hedgehog.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.spring.hedgehog.domain.Hedgehog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // loads the application context
@AutoConfigureMockMvc // create a MockMVC bean
@Sql(scripts = {"classpath:hedgehog-schema.sql", "classpath:hedgehog-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
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
    void testCreateMultiple() throws  Exception {
        String sonicJSON = this.mapper.writeValueAsString(List.of(new Hedgehog("Sonic", "blue", 15)));
        String responseBodyJSON = this.mapper.writeValueAsString(List.of(new Hedgehog(2, "Sonic", "blue", 15)));
        this.mvc.perform(
                        post("/hedgehog/createMultiple")
                                .content(sonicJSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(responseBodyJSON));
    }

    @Test
    void testRead() throws Exception {
        final int id = 1;
        String responseBody = this.mapper.writeValueAsString(new Hedgehog(id, "Silver", "silver", 12));
        this.mvc.perform(get("/hedgehog/get/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void testReadAll() throws Exception {
        final int id = 1;
        String responseBody = this.mapper.writeValueAsString(List.of(new Hedgehog(id, "Silver", "silver", 12)));
        this.mvc.perform(get("/hedgehog/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void testReadName() {
    }

    ;

    @Test
    void testUpdate() throws Exception {
        final int id = 1;
        String updated = this.mapper.writeValueAsString(new Hedgehog(id, "shadow", "black", 12));
        this.mvc.perform(
                        patch("/hedgehog/update/" + id)
                                .queryParam("name", "shadow")
                                .queryParam("colour", "black")
                                .queryParam("age", "12"))
                .andExpect(status().isOk())
                .andExpect(content().json(updated));
    }

    @Test
    void testDelete() throws Exception {
        final int id = 1;
        String responseBody = this.mapper.writeValueAsString(new Hedgehog(id, "Silver", "silver", 12));
        this.mvc.perform(delete("/hedgehog/remove/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    // Nice to have but not required:

    @Test
        // try and GET an object that doesn't exist
    void testNotFound() throws Exception {
        this.mvc.perform(get("/get/999")).andExpect(status().isNotFound());
    }

    @Test
        // try and create an object that doesn't pass validation and check that the error works
    void testFailCreate() throws Exception {
        String create = this.mapper.writeValueAsString(
                new Hedgehog("njvkdfjnbkgj", "jnbjgfkfnjbf", 4324234)
        );
        this.mvc.perform(
                post("/hedgehog/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(create)
        ).andExpect(status().is4xxClientError());
    }

    ;

}
