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
import javax.validation.Valid;

@RestController
public class Controller {

    @Autowired
    private AccountService accountService;
    
    /**
    * Creating First Saving Account For Customer.
    * @Param savingAccount
    * @return 
    */
    @PostMapping("/accounts/saving")
    public ResponseEntity createAccount(@Valid @RequestBody CustomerRequest customerRequest){
        Optional<Client> response = accountService.addSavingsAccount(customerRequest.getClient());
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    /**
    * Creating Current Account Based On Existing Account and Transfer Initial Credit.
    * @Param custId
    * @Param initialCredit
    * @return 
    */
    @GetMapping(path = "/accounts/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> addCurrentAccount(@RequestParam int custId , @RequestParam double intialCredit){
        Optional<Client> response = accountService.searchAndCreateCurrentAccount(custId, intialCredit);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    /**
    * Fetch Customer Info For Current Account.
    * @Param custId
    * @return 
    */
    @GetMapping("accounts/customerInfo")
    public ResponseEntity getCustomerDetails(@RequestParam int custId){
        Optional<Client> response = accountService.getCustomerInformationOfNewAccount(custId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
