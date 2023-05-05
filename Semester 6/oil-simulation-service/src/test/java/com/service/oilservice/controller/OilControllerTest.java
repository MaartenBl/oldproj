package com.service.oilservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.oilservice.models.EnergyModel;
import org.junit.jupiter.api.Test;
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

class OilControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private OilController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getAllOilProductionTest() throws Exception {
        mvc.perform(get("/oil/all")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(13)));
    }

    @Test
    public void getMonthOilProdTest() throws Exception {
        EnergyModel energyModel = new EnergyModel("2019MM12", 136, "Olieproducten_7");
        String expected = mapper.writeValueAsString(energyModel);

        mvc.perform(get("/oil/2019MM12")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    public void getMonthOilProdFailTest() throws Exception {
        mvc.perform(get("/oil/wrongParam")
                .contentType(MediaType.ALL))
                .andExpect(status().is4xxClientError());
    }



}