package proftaak.service.gasservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import proftaak.service.gasservice.model.EnergyModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class GasControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GasController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getAllTest() throws Exception {
        mvc.perform(get("/gas/all")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(13)));
    }

    @Test
    public void getPredictionTest() throws Exception {
        EnergyModel energyModel = new EnergyModel("Simulation", 3356683.42, "Aardgas_8");
        String expected = mapper.writeValueAsString(energyModel);

        mvc.perform(get("/gas/predict?nm3=1300000000")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    public void getPredictionWrongParamTest() throws Exception {
        mvc.perform(get("/gas/predict?nm3=FalseParam")
                .contentType(MediaType.ALL))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getMonthProdTest() throws Exception {
        EnergyModel energyModel = new EnergyModel("2019MM12", 5553.0, "Aardgas_8");
        String expected = mapper.writeValueAsString(energyModel);

        mvc.perform(get("/gas/2019MM12")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    public void getMonthGasProdFailTest() throws Exception {
        mvc.perform(get("/gas/wrongParam")
                .contentType(MediaType.ALL))
                .andExpect(status().is4xxClientError());
    }
}