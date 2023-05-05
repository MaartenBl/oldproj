package com.daliycodebuffer.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MQConfig {

    public static final String EXCHANGE = "topic_exchange";
    public static final String KEY_WEATHER = "weather.forecast";
    public static final String KEY_ENERGY_UPDATE = "energy.*.update";

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue queueAnonymousWeather(){
        return new AnonymousQueue();
    }
    @Bean
    public Queue queueAnonymousEnergyUpdate(){
        return new AnonymousQueue();
    }

    @Bean
    public Binding bindingWeather(TopicExchange topicExchange, @Qualifier("queueAnonymousWeather") Queue queueAnonymousWeather){
        return BindingBuilder
                .bind(queueAnonymousWeather)
                .to(topicExchange)
                .with(KEY_WEATHER); //listens to
    }
    @Bean
    public Binding bindingEnergyUpdate(TopicExchange topicExchange, @Qualifier("queueAnonymousEnergyUpdate") Queue queueAnonymousEnergyUpdate){
        return BindingBuilder
                .bind(queueAnonymousEnergyUpdate)
                .to(topicExchange)
                .with(KEY_ENERGY_UPDATE); //listens to
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
