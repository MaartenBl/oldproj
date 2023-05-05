package com.service.sunservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SunControllerTest {
    @Autowired
    private MockMvc mvc;


    @Test
    void getLastYear() throws Exception {
        mvc.perform(get("/sun/all")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    void getRepos() throws Exception {
        mvc.perform(get("/sun/2020")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    void getPrediction() throws Exception {
        mvc.perform(get("/sun/prediction")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk());
    }
}
