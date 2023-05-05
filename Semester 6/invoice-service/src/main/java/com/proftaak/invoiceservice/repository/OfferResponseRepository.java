package com.proftaak.invoiceservice.repository;

import com.proftaak.invoiceservice.model.BuyOfferResponseModel;
import com.proftaak.invoiceservice.model.international.OfferResponse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferResponseRepository extends CrudRepository<OfferResponse, String> {
    List<OfferResponse> findByOfferId(String offerId);
}
