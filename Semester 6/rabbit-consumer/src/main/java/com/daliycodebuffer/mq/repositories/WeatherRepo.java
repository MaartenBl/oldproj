package com.daliycodebuffer.mq.repositories;

import com.daliycodebuffer.mq.CustomMessage;
import com.daliycodebuffer.mq.models.Weather;
import com.daliycodebuffer.mq.repositories.interfaces.WeatherRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

import static com.daliycodebuffer.mq.RedisConfig.WEATHER_KEY;

@Repository
public class WeatherRepo implements WeatherRepository {

    @Autowired
    private RedisTemplate<String, Object> template;

    private Gson gson = new Gson();

    public void save(String weatherMsg){
        CustomMessage message = gson.fromJson(weatherMsg, CustomMessage.class);
        Weather[] weather = gson.fromJson(message.getMessage(), Weather[].class);
        try {
            template.opsForValue().set(WEATHER_KEY, weather);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Map<String, Weather> findAll(){
        //return template.opsForHash().values(HASH_KEY);
        return null;
    }

    @Override
    public void update(Weather weather) {

    }

    @Override
    public void delete(String name) {

    }

    @Override
    public Weather findByTemp(int minTemp) {
        return null;
    }

/*    public Weather findProductById(int weatherID){
        //template.opsForValue().get(weatherID);
        return (Weather) template.opsForHash().get(*//*HASH_KEY,*//* weatherID);
    }*/

/*    public String deleteProduct(int windSpeed){
        template.opsForHash().delete(*//*HASH_KEY,*//* windSpeed);
        return  "Product Removed";
    }*/

}
