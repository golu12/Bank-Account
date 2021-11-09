package com.international.bank.demo.controller;

import com.international.bank.demo.model.Client;
import com.international.bank.demo.model.CustomerRequest;
import com.international.bank.demo.model.TransactionResponse;
import com.international.bank.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts/saving")
    public ResponseEntity createAccount(@RequestBody CustomerRequest customerRequest){
        Optional<Client> response = accountService.addSavingsAccount(customerRequest.getClient());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(path = "/accounts/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> addCurrentAccount(@RequestParam int custId , @RequestParam double intialCredit){
        Optional<Client> response = accountService.searchAndCreateCurrentAccount(custId, intialCredit);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("accounts/current")
    public ResponseEntity getCustomerDetails(@RequestParam int custId){
        Optional<Client> response = accountService.getCustomerInformationOfNewAccount(custId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
