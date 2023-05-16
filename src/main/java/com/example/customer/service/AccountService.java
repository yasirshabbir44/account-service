package com.example.customer.service;

import com.example.customer.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public synchronized boolean transfer(Account from, Account to, Double amount) {

        // synchronizing from this point, since balances are checked
        synchronized(from) {
            synchronized(to) {
                if (amount < 0)
                    throw new IllegalArgumentException("Negative value not allowed");


                if( from.getBalance() < amount){
                    throw new IllegalArgumentException("Insufficent balance");
                }

                to.setBalance(to.getBalance() + amount);
                from.setBalance(from.getBalance() - amount);
                return true;
            }
        }
    }
}
