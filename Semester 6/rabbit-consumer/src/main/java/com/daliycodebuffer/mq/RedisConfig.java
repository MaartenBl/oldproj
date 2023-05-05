package com.daliycodebuffer.mq;

import com.daliycodebuffer.mq.models.Weather;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;


@Configuration
public class RedisConfig {
    public static final String WEATHER_KEY = "Weather";
    public static final String WIND_KEY = "Wind";
    public static final String SUN_KEY = "Sun";
    public static final String GAS_KEY = "Gas";
    //Uses springboot built in lettuce
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        /*template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));*/
        return template;
    }
}
