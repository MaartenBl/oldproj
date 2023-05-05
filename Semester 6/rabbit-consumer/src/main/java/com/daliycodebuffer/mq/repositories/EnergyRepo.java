package com.daliycodebuffer.mq.repositories;

import com.daliycodebuffer.mq.CustomMessage;
import com.daliycodebuffer.mq.models.EnergyModel;
import com.daliycodebuffer.mq.models.Weather;
import com.daliycodebuffer.mq.repositories.interfaces.IEnergyRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.daliycodebuffer.mq.RedisConfig.*;

@Repository
public class EnergyRepo implements IEnergyRepository {

    @Autowired
    private RedisTemplate<String, Object> template;
    private Gson gson = new Gson();

    @Override
    public void save(String energyMsg) {
        try {
            CustomMessage jsonMessage = gson.fromJson(energyMsg, CustomMessage.class);
            List<EnergyModel> energy = Arrays.asList(gson.fromJson(jsonMessage.getMessage(),EnergyModel[].class));
            template.opsForValue().set(sourceParser(energy), energy); //Should throw on empty string
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Map<String, EnergyModel> findAll() {
        return null;
    }

    @Override
    public void update(EnergyModel energy) {

    }

    @Override
    public void delete(String name) {

    }

    @Override
    public EnergyModel findByEnergy(int id) {
        return null;
    }

    private String sourceParser(List<EnergyModel> energy ){
        if (energy.get(0).getSource().contains("Aardgas")){
            return GAS_KEY;
        } else if (energy.get(0).getSource().contains("WindenergieTotaal")){
            return WIND_KEY;
        }else if (energy.get(0).getSource().contains("Zonnestroom")){
            return SUN_KEY;
        }
        return "";
    }
}
