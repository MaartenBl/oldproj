package com.service.meteoserverservice;

import java.util.Date;
import java.util.UUID;

import com.google.gson.Gson;
import com.service.meteoserverservice.controller.MeteoServer;
import com.service.meteoserverservice.model.CustomMessage;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherUpdater {
    @Autowired
    private RabbitTemplate template;

    private final MeteoServer restServer;
    private Gson gson = new Gson();
    public WeatherUpdater() throws Exception {
        this.restServer = new MeteoServer();
    }
    //@Scheduled(cron="0 0 6 * * MON-SUN")
    @Scheduled(fixedRate = 999999) //<--Every ~16 minutes    @Scheduled(cron="0 6 * * ?") //Updates every morning at 6:00AM
    public void UpdateWeatherInfo() throws Exception {
        CustomMessage message = new CustomMessage();
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageData(new Date());

        ResponseEntity response = restServer.getPrediction(); //Kijken of ik het als DTO met getPrediction() kan versturen of als JSON object met returnForecast()
        String jsonResponse = gson.toJson(response.getBody());
        System.out.println("API RESPONSE: " + jsonResponse);

        message.setMessage(jsonResponse);
        String jsonMessage = gson.toJson(message);
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.KEY_WEATHER, jsonMessage);
    }

}
