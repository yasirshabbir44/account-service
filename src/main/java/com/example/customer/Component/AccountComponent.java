package com.example.customer.Component;

import com.example.customer.model.Account;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/*
 * Component used to indicate that a bean should run when it is contained within a SpringApplication.
 * Load all accounts from accounts-mocks.json file inside resources/json into Collection
 * */
@Component
public
class AccountComponent implements ApplicationRunner {

    private List<Account> accounts = Collections.emptyList();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Account>> typeReference = new TypeReference<>() {
        };
        String FILE_PATH = "/json/accounts-mock.json";
        InputStream inputStream = TypeReference.class.getResourceAsStream(FILE_PATH);
        this.accounts = mapper.readValue(inputStream, typeReference);
    }


    public List<Account> getAccounts() {
        return this.accounts;
    }
}