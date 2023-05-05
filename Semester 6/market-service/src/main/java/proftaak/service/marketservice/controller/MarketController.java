package proftaak.service.marketservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import proftaak.service.marketservice.model.BuyOfferResponseModel;
import proftaak.service.marketservice.model.EnergyInOut;
import proftaak.service.marketservice.model.Transaction;
import proftaak.service.marketservice.model.TransactionType;
import proftaak.service.marketservice.model.international.BuyOfferRequest;
import proftaak.service.marketservice.model.international.BuyOfferResponse;
import proftaak.service.marketservice.model.international.CreateSellOfferRequest;
import proftaak.service.marketservice.model.international.OfferResponse;
import proftaak.service.marketservice.rabbit.MQConfig;
import proftaak.service.marketservice.rabbit.MarketUpdater;

import javax.websocket.ClientEndpoint;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@ClientEndpoint
@Controller
@RequestMapping(value = "/market")
public class MarketController {
    private final String OUR_REGION =  "North";
    private final String MARKET_API = "https://web20210526143754.azurewebsites.net";
    private final String MARKET_API_HEROKU = "http://shielded-bastion-42632.herokuapp.com/swagger/index.html";
    private final String TOKEN = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjI5NGM2MzA0LTcyNzgtNDZlMy05MzhkLTBjMDRiYzkyZDAxZCJ9.u5WK0kPRAb-S_4C5P7VWQvpFPtIPSz4oEhWjZAsL0jSgxZerSYnG_qi7d0Gf4hQSSajIoE8rTxpGoLIBlr1n9w";
    private Gson gson;

    @Autowired
    private MarketUpdater updater;

    //BUY GET
    @GetMapping(path = "/api/buy")
    public @ResponseBody
    BuyOfferRequest getBuy(){
/*        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(MARKET_API_HEROKU + "/api/buy"))
                .build();
        var response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);*/
        return new BuyOfferRequest();
    }

    //BUY POST
    @PostMapping(path = "/api/buy")
    public @ResponseBody
    BuyOfferResponse postBuy(String offerId, Integer amount ) throws Exception {
        String token = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjI5NGM2MzA0LTcyNzgtNDZlMy05MzhkLTBjMDRiYzkyZDAxZCJ9.u5WK0kPRAb-S_4C5P7VWQvpFPtIPSz4oEhWjZAsL0jSgxZerSYnG_qi7d0Gf4hQSSajIoE8rTxpGoLIBlr1n9w";

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://shielded-bastion-42632.herokuapp.com/api/buy")
                .header("Authorization", "Bearer " + token)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\"offerId\":\""+ offerId+"\",\"amount\":"+ amount +"}")
                .asString();
        System.out.println(response.getBody());
        BuyOfferResponse buyOfferResponse = new BuyOfferResponse();
        try {
            buyOfferResponse = new ObjectMapper().readValue(response.getBody(), BuyOfferResponse.class);
        }catch (Exception e){
            System.out.println(e.toString());
        }
       updater.PackageAndSend(MQConfig.MARKET_BUY_OFFER_RESPONSE, buyOfferResponse);
        return buyOfferResponse;
    }
/*    @GetMapping(path = "/api/buy/test")
    public @ResponseBody
    BuyOfferResponseModel[] getTest() throws Exception  {
        OfferResponse emptyOffer = new OfferResponse();
        BuyOfferResponseModel response1 = new BuyOfferResponseModel("buyer1", "10", 1, 10);
        BuyOfferResponseModel response2 = new BuyOfferResponseModel("buyer2", "20", 2, 20);

        BuyOfferResponseModel[] buyOfferResponsesArray = new BuyOfferResponseModel[2];
        buyOfferResponsesArray[0] = response1;
        buyOfferResponsesArray[1] = response2;
        updater.PackageAndSend(MQConfig.MARKET_BUY_OFFER_RESPONSE, buyOfferResponsesArray);
        return buyOfferResponsesArray;
    }*/
    //BUY GET SALES
    @GetMapping(path = "/api/buy/sales")
    public @ResponseBody
    BuyOfferResponse[] getBuySales() throws Exception {
        String token = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjI5NGM2MzA0LTcyNzgtNDZlMy05MzhkLTBjMDRiYzkyZDAxZCJ9.u5WK0kPRAb-S_4C5P7VWQvpFPtIPSz4oEhWjZAsL0jSgxZerSYnG_qi7d0Gf4hQSSajIoE8rTxpGoLIBlr1n9w";

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("http://shielded-bastion-42632.herokuapp.com/api/buy/sales")
                .header("Authorization", "Bearer "+token)
                .asString();

        BuyOfferResponse[] buyOfferResponses = new BuyOfferResponse[0];
        try {
            buyOfferResponses = new ObjectMapper().readValue(response.getBody(), BuyOfferResponse[].class);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return buyOfferResponses;
    }

    //SELL POST
    @PostMapping(path = "/api/offer")
    public @ResponseBody
    CreateSellOfferRequest postOffer(@RequestParam double amount){
  /*      CreateSellOfferRequest offer = new CreateSellOfferRequest(amount);

*//*
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(MARKET_API_HEROKU + "/api/offer"))
                .build();

        var response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
*//*


        RestTemplate restTemplate = new RestTemplate();
        String json = gson.toJson(offer);
        String result = restTemplate.postForObject(URI.create(MARKET_API_HEROKU + "/api/offer"),json, String.class);

        System.out.println(result);*/

        return new CreateSellOfferRequest(3);
    }

    @PostMapping(path = "/")
    public @ResponseBody
    EnergyInOut getPredictionWithParams(
            @RequestParam String region,
            @RequestParam double quantity
    ) throws Exception {
        if (region == "") region = OUR_REGION;
        EnergyInOut energyModel = new EnergyInOut(region, quantity);

        //saveTransaction(TransactionType.BUY, quantity);

        return energyModel;
    }

    @GetMapping(path = "/history")
    public @ResponseBody
    List<Transaction> getHistoryOfTransactions(){
        List<Transaction> transactions = new ArrayList<>();
        //hier alle transacties ophalen

        return transactions;
    }

    private void saveTransaction(TransactionType transactionType, double energyQuantity) throws Exception {
        Transaction transaction = new Transaction(OUR_REGION, transactionType, energyQuantity);
        System.out.println(transaction);
        //save transaction
        //updater.PublishTransactionInfo(transactionType, transaction);
    }
}
