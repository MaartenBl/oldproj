package proftaak.service.gasservice.rabbit;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.var;
import proftaak.service.gasservice.controller.GasController;
import proftaak.service.gasservice.model.CustomMessage;
import proftaak.service.gasservice.model.EnergyModel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class GasUpdater {

    @Autowired
    private RabbitTemplate template;

    private final GasController gasController;
    private Gson gson = new Gson();

    public GasUpdater() throws Exception {
        this.gasController = new GasController();
    }

    @Scheduled(fixedRate = 999999) //Every 16 minutes
    //@Scheduled(cron="0 0 6 * * MON-SUN")
    public void UpdateWeatherInfo() throws Exception {
        CustomMessage message = new CustomMessage();
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageData(new Date());

        List<EnergyModel> response = gasController.getAll(); //Kijken of ik het als DTO met getPrediction() kan versturen of als JSON object met returnForecast()
        String jsonResponse = gson.toJson(response);
        System.out.println("API RESPONSE: " + jsonResponse);

        message.setMessage(jsonResponse);
        String customMessageJson = gson.toJson(message);
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.KEY_GAS, customMessageJson);
    }
}
