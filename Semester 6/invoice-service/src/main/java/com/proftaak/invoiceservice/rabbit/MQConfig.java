package com.proftaak.invoiceservice.rabbit;

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

    static final String EXCHANGE = "topic_exchange";
    public static final String MARKET_BUY_OFFER_REQUEST = "market.buyofferrequest.noord";
    public static final String MARKET_BUY_OFFER_RESPONSE = "market.buyofferresponse.noord";
    public static final String MARKET_SELL_OFFER_REQUEST = "market.createsellofferrequest.noord";
    public static final String MARKET_OFFER_RESPONSE = "market.offerresponse.noord";

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

/*Setting up multiple queues keeps the queues short, in line with rabbitmq design.*/
    @Bean
    public Queue queueAnonymousBalanceResponse(){
        return new AnonymousQueue();
    }
    @Bean
    public Queue queueAnonymousBuyOfferRequest(){
        return new AnonymousQueue();
    }
    @Bean
    public Queue queueAnonymousBuyOfferResponse(){
        return new AnonymousQueue();
    }
    @Bean
    public Queue queueAnonymousOfferResponse(){
        return new AnonymousQueue();
    }
    @Bean
    public Queue queueAnonymousCreateSellOfferRequest(){
        return new AnonymousQueue();
    }

    @Bean
    public Binding bindingBuyOfferRequest(TopicExchange topicExchange, @Qualifier("queueAnonymousBuyOfferRequest") Queue queueAnonymousBuyOfferRequest){
        return BindingBuilder
                .bind(queueAnonymousBuyOfferRequest)
                .to(topicExchange)
                .with(MARKET_BUY_OFFER_REQUEST); //listens to
    }

    @Bean
    public Binding bindingBuyOfferResponse(TopicExchange topicExchange, @Qualifier("queueAnonymousBuyOfferResponse") Queue queueAnonymousBuyOfferResponse){
        return BindingBuilder
                .bind(queueAnonymousBuyOfferResponse)
                .to(topicExchange)
                .with(MARKET_BUY_OFFER_RESPONSE); //listens to
    }

    @Bean
    public Binding bindingCreateAccountRequest(TopicExchange topicExchange, @Qualifier("queueAnonymousOfferResponse") Queue queueAnonymousOfferResponse){
        return BindingBuilder
                .bind(queueAnonymousOfferResponse)
                .to(topicExchange)
                .with(MARKET_OFFER_RESPONSE); //listens to
    }

    @Bean
    public Binding bindingCreateSellOfferRequest(TopicExchange topicExchange, @Qualifier("queueAnonymousCreateSellOfferRequest") Queue queueAnonymousCreateSellOfferRequest){
        return BindingBuilder
                .bind(queueAnonymousCreateSellOfferRequest)
                .to(topicExchange)
                .with(MARKET_SELL_OFFER_REQUEST); //listens to
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
