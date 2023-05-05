package com.proftaak.invoiceservice.rabbit;

import com.google.gson.Gson;
import com.netflix.discovery.converters.Auto;
import com.proftaak.invoiceservice.model.*;
/*import com.proftaak.invoiceservice.repository.InvoiceRepository;*/
import com.proftaak.invoiceservice.model.international.*;
import com.proftaak.invoiceservice.repository.BuyOfferResponseModelRepository;
import com.proftaak.invoiceservice.repository.OfferResponseRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private Gson gson = new Gson();

    @Autowired
    BuyOfferResponseModelRepository buyOfferResponseModelRepository;

    @Autowired
    OfferResponseRepository offerResponseRepository;


    @RabbitListener(queues = "#{queueAnonymousBuyOfferRequest.name}") //Listens to MarketBuy messages
    public void receiveBuyOfferRequest(String jsonMessage) {
        CustomMessage message = gson.fromJson(jsonMessage, CustomMessage.class);
        BuyOfferRequest response = gson.fromJson(message.getMessage(), BuyOfferRequest.class );
        System.out.println("[RECEIVED OBJECT: ]" + response);
    }

    @RabbitListener(queues = "#{queueAnonymousBuyOfferResponse.name}") //Listens to MarketBuy messages
    public void receiveBuyOfferResponse(String jsonMessage) {
        System.out.println("[INCOMING:] " + jsonMessage);
        CustomMessage message = gson.fromJson(jsonMessage, CustomMessage.class);
        BuyOfferResponse response = gson.fromJson(message.getMessage(), BuyOfferResponse.class );
        System.out.println("[RECEIVED OBJECT: ]" + response);
        System.out.println("offerID: " + response.getOffer().getId());
        BuyOfferResponseModel model = new BuyOfferResponseModel( response.getBuyerName(), response.getOffer().getId(), response.getAmount(),response.getPriceTotal());
        buyOfferResponseModelRepository.save(model);
        System.out.println("[SAVED OBJECT: ]" + model);
        OfferResponse offer = new OfferResponse(response.getOffer().getId(), response.getOffer().getSeller(), response.getOffer().getAmount(),response.getOffer().getAmountTotal(),response.getOffer().getPrice());
        offerResponseRepository.save(offer);
        System.out.println("[SAVED OFFER: ]" + offer);
    }

    @RabbitListener(queues = "#{queueAnonymousCreateSellOfferRequest.name}") //Listens to MarketBuy messages
    public void receiveCreateSellOfferRequest(String jsonMessage) {
        CustomMessage message = gson.fromJson(jsonMessage, CustomMessage.class);
        CreateSellOfferRequest response = gson.fromJson(message.getMessage(), CreateSellOfferRequest.class );
        System.out.println("[RECEIVED OBJECT: ]" + response);
    }

    @RabbitListener(queues = "#{queueAnonymousOfferResponse.name}") //Listens to MarketBuy messages
    public void receiveOfferResponse(String jsonMessage) {
        CustomMessage message = gson.fromJson(jsonMessage, CustomMessage.class);
        OfferResponse response = gson.fromJson(message.getMessage(), OfferResponse.class );
        System.out.println("[RECEIVED OBJECT: ]" + response);
    }
}
