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



	@Component
	class AccountComponent implements ApplicationRunner{

		@Override
		public void run(ApplicationArguments args) throws Exception {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/accounts-mock.json");
			List<Account> accounts = mapper.readValue(inputStream,typeReference);
			System.out.println("Users Saved!");
		}
	}


	record Account(String id, String name, Float balance){}

}
