package com.daliycodebuffer.mq;

import com.daliycodebuffer.mq.models.Weather;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.daliycodebuffer.mq.RedisConfig.WEATHER_KEY;

@RestController
@RequestMapping("/simulation")
@CrossOrigin(origins = "http://localhost:3000")
public class SimulationController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private Gson gson = new Gson();
    @PutMapping("/put")
    public ResponseEntity save(@RequestBody String weatherBody) {
        Weather weather = gson.fromJson(weatherBody, Weather.class);
        redisTemplate.opsForValue().set(WEATHER_KEY, weather);
        Weather testWeather = (Weather) redisTemplate.opsForValue().get(weather.getId());

        if (testWeather.getId() == weather.getId()){
            return ResponseEntity.status(HttpStatus.OK).body("Object saved");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Object not saved");
        }
    }

    @GetMapping("get/{key}")
    public ResponseEntity findById(@PathVariable String key) {
        if (redisTemplate.opsForValue().get(key) != null){
            System.out.println(redisTemplate.opsForValue().get(key));
            return ResponseEntity.status(HttpStatus.OK).body(redisTemplate.opsForValue().get(key));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }
}
