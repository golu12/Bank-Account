package com.international.bank.demo.service;

import com.international.bank.demo.model.Account;
import com.international.bank.demo.model.Client;
import com.international.bank.demo.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    private ClientRepository clientRepository;

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);



    @Override
    public Optional<Client> addSavingsAccount(Client client) {
        if(clientRepository != null && client != null){
            Client result = clientRepository.save(client);
            if(result != null){
                return Optional.of(result);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> searchAndCreateCurrentAccount(int custId, double initialCredit) {
        Client client = null;
        Account account = null;
        try{
        List<Client> value = clientRepository.getCustomerRecord(custId);
        if(!value.get(0).getAccounts().get(0).getAccountNumber().isEmpty()){
            client = new Client();
            account = new Account();
            client.setId(value.get(0).getId());
            client.setCustomerId(value.get(0).getCustomerId());
            client.setFirstName(value.get(0).getFirstName());
            client.setLastName(value.get(0).getLastName());
            client.setAddress(value.get(0).getAddress());
            client.setBranch(value.get(0).getBranch());
            client.setEmailId(value.get(0).getEmailId());
            client.setIfscCode(value.get(0).getIfscCode());
            account.setAccountNumber("CAP089039458");
            account.setAccountType("current");
            if(initialCredit > 0){
                account.setBalance(initialCredit);
            }else {
                account.setBalance(0);
            }
            account.setCustomerId(value.get(0).getAccounts().get(0).getCustomerId());
            account.setPid(904);
            client.setAccounts(Collections.singletonList(account));
            return addSavingsAccount(client);
        }

        }catch(Exception e){
         LOG.error("Error while creating current account", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> getCustomerInformationOfNewAccount(int custId) {
        if(clientRepository != null){
            Client result = clientRepository.getCustomerDetails(custId);
            if(result != null){
                return Optional.of(result);
            }
        }
        return Optional.empty();
    }
}
