package com.example.customer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }



    /*
    * Component used to indicate that a bean should run when it is contained within a SpringApplication.
    * Load all accounts from accounts-mocks.json file inside resources/json into Collection
    * */
    @Component
    class AccountComponent implements ApplicationRunner {

        private final String FILE_PATH = "/json/accounts-mock.json";

        @Override
        public void run(ApplicationArguments args) throws Exception {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Account>> typeReference = new TypeReference<>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream(FILE_PATH);
            List<Account> accounts = mapper.readValue(inputStream, typeReference);
        }
    }


    record Account(String id, String name, Float balance) {
    }

}
