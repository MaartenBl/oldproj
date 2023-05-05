package com.service.windservice.rabbit;

import com.google.gson.Gson;
import com.service.windservice.controller.WindController;
import com.service.windservice.model.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class WindUpdater {

    @Autowired
    private RabbitTemplate template;

    private final WindController windController;
    private Gson gson = new Gson();

    public WindUpdater() throws Exception {
        this.windController = new WindController();
    }
    @Scheduled(fixedRate = 999999) //every ~16 minutes
    //@Scheduled(cron="0 0 6 * * MON-SUN")
    public void UpdateWeatherInfo() throws Exception {
        CustomMessage message = new CustomMessage();
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageData(new Date());

        ResponseEntity response = windController.getPrediction(); //Kijken of ik het als DTO met getPrediction() kan versturen of als JSON object met returnForecast()
        String jsonResponse = gson.toJson(response.getBody());
        System.out.println("API RESPONSE: " + jsonResponse);

        message.setMessage(jsonResponse);
        String customMessageJson = gson.toJson(message);
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.KEY_WIND, customMessageJson);
    }
}
