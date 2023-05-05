package com.service.meteoserverservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MeteoServerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private MeteoServer controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getAllTest() throws Exception {
        mvc.perform(get("/meteoserver/eindhoven")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllFailTest() throws Exception {
        mvc.perform(get("/meteoserver/")
                .contentType(MediaType.ALL))
                .andExpect(status().is4xxClientError());
    }

}