package com.daliycodebuffer.mq;

import com.daliycodebuffer.mq.repositories.WeatherRepo;
import com.daliycodebuffer.mq.repositories.interfaces.IEnergyRepository;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private WeatherRepo weatherRepo;
    @Autowired
    private IEnergyRepository energyRepository;

    @RabbitListener(queues = "#{queueAnonymousWeather.name}") //Listens to weatherservice api
    public void receiveWeather(String jsonMessage){
        weatherRepo.save(jsonMessage);
        System.out.println("[WEATHER] Message: " + jsonMessage);
    }
    @RabbitListener(queues = "#{queueAnonymousEnergyUpdate.name}") //Listens to all energies that send an update
    public void receiveEnergyUpdate(String jsonMessage){
        //Save to DBG
        energyRepository.save(jsonMessage);
        System.out.println("[ENERGY] Message: " + jsonMessage);
    }
}
