package com.example.customer.component;

import com.example.customer.model.Account;
import com.example.customer.repository.AccountRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/*
 * Component used to indicate that a bean should run when it is contained within a SpringApplication.
 * Load all accounts from accounts-mocks.json file inside resources/json into Collection
 * */
@Component
@AllArgsConstructor
@Slf4j
public
class AccountComponent implements ApplicationRunner {


    private final AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>() {
        };
        String FILE_PATH = "/json/accounts-mock.json";
        InputStream inputStream = TypeReference.class.getResourceAsStream(FILE_PATH);
        List<Account> accounts = mapper.readValue(inputStream, typeReference);

        accountRepository.saveAll(accounts);

        log.info("/////////////////////////////////////");
        log.info("System is ready to make a transfer");
        log.info("Total number of record imported : {}", accounts.size());
        log.info("/////////////////////////////////////");

    }

}