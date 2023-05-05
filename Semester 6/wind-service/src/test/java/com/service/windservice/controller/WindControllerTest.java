package com.service.windservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.windservice.model.EnergyModel;
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
class WindControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WindController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getAllTest() throws Exception {
        mvc.perform(get("/wind/all")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(13)));
    }

    @Test
    public void getPredictionTest() throws Exception {
        mvc.perform(get("/wind/prediction")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void getMonthProdTest() throws Exception {
        //expected
        EnergyModel energyModel = new EnergyModel("2019MM12", 1478, "WindenergieTotaal_12");
        String expected = mapper.writeValueAsString(energyModel);

        mvc.perform(get("/wind/2019MM12")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    public void getMonthWindProdFailTest() throws Exception {
        mvc.perform(get("/wind/wrongParam")
                .contentType(MediaType.ALL))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getPredictTest() throws Exception {
        //params
        double windspeed = 3;
        int turbines = 50;
        double rotarM2 = 60;
        double airDens = 1.25;

        //expected
        EnergyModel energyModel = new EnergyModel("Simulation", 50.62, "WindenergieTotaal_12");
        String expected = mapper.writeValueAsString(energyModel);

        mvc.perform(get("/wind/predict")
                .param("windspeed", String.valueOf(windspeed))
                .param("turbines", String.valueOf(turbines))
                .param("rotar", String.valueOf(rotarM2))
                .param("air", String.valueOf(airDens))
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    public void getPredictWithFalseParamTest() throws Exception {
        //params
        String windspeed = "false param";
        String turbines = "false param";
        String rotarM2 = "false param";
        String airDens = "false param";

        mvc.perform(get("/wind/predict")
                .param("windspeed", windspeed)
                .param("turbines", turbines)
                .param("rotar", rotarM2)
                .param("air", airDens)
                .contentType(MediaType.ALL))
                .andExpect(status().is4xxClientError());
    }
}