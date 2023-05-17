package com.example.customer.service;

import com.example.customer.exception.InsufficientBalanceException;
import com.example.customer.model.Account;
import com.example.customer.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransferService {

    private final AccountRepository accountRepository;

    @Transactional
    public synchronized void transfer(String from, String to, Double amount) {


        Account fromAccount = accountRepository.findById(from).orElseThrow(() -> new IllegalArgumentException("From Account not found"));
        Account toAccount = accountRepository.findById(to).orElseThrow(() -> new IllegalArgumentException("To  Account not found"));
        // synchronizing from this point, since balances are checked
        if (amount < 0)
            throw new IllegalArgumentException("Negative value not allowed");


        if (fromAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Account current balance is not available to withdraw. current balance: %s, amount: %s",
                    fromAccount.getBalance(),
                    amount);
        }

        toAccount.setBalance(toAccount.getBalance() + amount);
        fromAccount.setBalance(fromAccount.getBalance() - amount);

        accountRepository.save(toAccount);
        accountRepository.save(fromAccount);
    }

}
