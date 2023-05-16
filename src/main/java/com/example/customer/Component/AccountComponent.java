package com.example.customer.Component;

import com.example.customer.model.Account;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * Component used to indicate that a bean should run when it is contained within a SpringApplication.
 * Load all accounts from accounts-mocks.json file inside resources/json into Collection
 * */
@Component
public
class AccountComponent implements ApplicationRunner {


    private Map<String, Account> accounts;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>() {
        };
        String FILE_PATH = "/json/accounts-mock.json";
        InputStream inputStream = TypeReference.class.getResourceAsStream(FILE_PATH);
        List<Account> accounts = mapper.readValue(inputStream, typeReference);

        this.accounts = accounts.parallelStream()
                .collect(Collectors.toMap(Account::getId, Function.identity()));

        System.out.println();
    }


    public Map<String, Account> getAccounts() {
        return this.accounts;
    }
}