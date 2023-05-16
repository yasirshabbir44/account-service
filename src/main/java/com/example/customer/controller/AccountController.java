package com.example.customer.controller;

import com.example.customer.Component.AccountComponent;
import com.example.customer.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public record AccountController(AccountComponent accountComponent) {

    @GetMapping("/accounts")
    public List<Account> get() {
        return accountComponent.getAccounts();
    }
}
