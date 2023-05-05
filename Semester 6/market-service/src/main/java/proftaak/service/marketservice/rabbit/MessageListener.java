package proftaak.service.marketservice.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proftaak.service.marketservice.model.CustomMessage;

@Component
public class MessageListener {

    @RabbitListener(queues = "#{queueAnonymousConsumption.name}") //Listens to consumptionservice
    public void receiveConsumption(CustomMessage message){
        // Do something with consumption data
    }
/*    @RabbitListener(queues = "#{queueAnonymousEnergyUpdate.name}") //Listens to all energies that send an update
    public void receiveEnergyUpdate(CustomMessage message){

    }*/
}
