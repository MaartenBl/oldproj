package com.proftaak.invoiceservice.controller;

import com.proftaak.invoiceservice.model.BuyOfferResponseModel;
import com.proftaak.invoiceservice.model.Transaction;
/*import com.proftaak.invoiceservice.repository.InvoiceRepository;*/
import com.proftaak.invoiceservice.model.international.OfferResponse;
import com.proftaak.invoiceservice.repository.BuyOfferResponseModelRepository;
import com.proftaak.invoiceservice.repository.OfferResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.ClientEndpoint;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@ClientEndpoint
@Controller
@RequestMapping(value = "/invoice")
public class InvoiceController {

    @Autowired
    private BuyOfferResponseModelRepository buyOfferResponseModelRepository;

    @Autowired
    private OfferResponseRepository offerResponseRepository;

    @GetMapping(path = "/getoffers")
    public @ResponseBody
    List<OfferResponse> getOffers(String offerId){
        return offerResponseRepository.findByOfferId(offerId);
    }

    @GetMapping(path = "/gethistory")
    public @ResponseBody
    List<BuyOfferResponseModel> getHistory(String name){
        return buyOfferResponseModelRepository.findByBuyerName(name);
    }
}
