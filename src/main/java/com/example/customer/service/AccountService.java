package com.example.customer.service;

import com.example.customer.Component.AccountComponent;
import com.example.customer.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountComponent accountComponent;

    public synchronized void transfer(String from, String to, Double amount) {

        Account fromAccount = accountComponent.getAccounts().get(from);
        Account toAccount = accountComponent.getAccounts().get(to);
        // synchronizing from this point, since balances are checked
        synchronized (from) {
            synchronized (to) {
                if (amount < 0)
                    throw new IllegalArgumentException("Negative value not allowed");


                if (fromAccount.getBalance() < amount) {
                    throw new IllegalArgumentException("Insufficent balance");
                }

                toAccount.setBalance(toAccount.getBalance() + amount);
                fromAccount.setBalance(fromAccount.getBalance() - amount);
            }
        }
    }
}
