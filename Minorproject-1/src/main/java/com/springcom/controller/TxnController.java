package com.springcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springcom.exception.TxnServiceException;
import com.springcom.request.CreateReturnTxnRequest;
import com.springcom.request.CreateTxnRequest;
import com.springcom.response.TxnSettlementResponse;
import com.springcom.service.TxnService;

import jakarta.validation.Valid;

public class TxnController {
	@Autowired TxnService txnService;

    @PostMapping("/create")
    public String create(@RequestBody CreateTxnRequest createTxnRequest) throws TxnServiceException {
    return	txnService.create(createTxnRequest);
    }
    @PostMapping("/return")
    public TxnSettlementResponse returnBook(@RequestBody @Valid CreateReturnTxnRequest createReturnTxnRequest) throws TxnServiceException {
        return txnService.returnBook(createReturnTxnRequest);
    }
}
