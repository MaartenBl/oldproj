package com.service.sunservice.rabbit;

import com.google.gson.Gson;
import com.service.sunservice.controller.SunController;
import com.service.sunservice.model.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class SunUpdater {

    @Autowired
    private RabbitTemplate template;
    private Gson gson = new Gson();
    private final SunController sunController;

    public SunUpdater() throws Exception {
        this.sunController = new SunController();
    }
    //@Scheduled(cron="0 0 6 * * MON-SUN")
    @Scheduled(fixedRate = 999999)
    public void UpdateWeatherInfo() throws Exception {
        CustomMessage message = new CustomMessage();
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageData(new Date());

        var response = sunController.getPrediction(); //Kijken of ik het als DTO met getPrediction() kan versturen of als JSON object met returnForecast()
        System.out.println("API RESPONSE: " + response);
        String jsonResponse = gson.toJson(response.getBody());

        message.setMessage(jsonResponse);
        String customMessageJson = gson.toJson(message);
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.KEY_SUN, customMessageJson);
    }
}
