package proftaak.service.marketservice.rabbit;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import proftaak.service.marketservice.model.CustomMessage;
import proftaak.service.marketservice.model.Transaction;
import proftaak.service.marketservice.model.TransactionType;

import java.util.Date;
import java.util.UUID;

@Component
public class MarketUpdater {
    private Gson gson = new Gson();
    @Autowired
    private RabbitTemplate template;


    public void PackageAndSend(String queueName, Object transaction) throws Exception {
        String jsonResponse = gson.toJson(transaction);

        CustomMessage message = new CustomMessage();
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageData(new Date());
        message.setMessage(jsonResponse);

        String jsonMessage = gson.toJson(message);
        template.convertAndSend(MQConfig.EXCHANGE, queueName, jsonMessage);
    }
}
