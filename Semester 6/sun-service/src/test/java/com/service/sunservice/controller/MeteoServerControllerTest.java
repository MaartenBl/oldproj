package com.service.sunservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class MeteoServerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MeteoServerController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getTest() throws Exception {
        List<String> weather = new ArrayList<>();
        String expected = "";

        mvc.perform(get("/meteoserver/eindhoven")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    public void getWithWrongParamTest() throws Exception {
        mvc.perform(get("/meteoserver/000")
                .contentType(MediaType.ALL))
                .andExpect(status().is4xxClientError());
    }
}