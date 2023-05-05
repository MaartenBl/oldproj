package com.service.nuclearservice.controller;

import com.service.nuclearservice.model.EnergyModel;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class NuclearControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private NuclearController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getAllNuclearProductionTest() throws Exception {
        mvc.perform(get("/nuclear/all")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(13)));
    }

    @Test
    public void getMonthProdTest() throws Exception {
        EnergyModel energyModel = new EnergyModel("2019MM12", 360, "Kernenergie_4");
        String expected = mapper.writeValueAsString(energyModel);

        mvc.perform(get("/nuclear/2019MM12")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    public void getMonthProdFailTest() throws Exception {
        mvc.perform(get("/nuclear/wrongParam")
                .contentType(MediaType.ALL))
                .andExpect(status().is4xxClientError());
    }
}