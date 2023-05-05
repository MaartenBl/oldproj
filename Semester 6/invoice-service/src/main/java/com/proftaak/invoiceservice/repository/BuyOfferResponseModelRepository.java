package com.proftaak.invoiceservice.repository;

import com.proftaak.invoiceservice.model.BuyOfferResponseModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuyOfferResponseModelRepository extends CrudRepository<BuyOfferResponseModel, Integer> {
    List<BuyOfferResponseModel> findByBuyerName(String buyerName);
}
