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

    /*
    * Method accepting three parameter fromAccountId( UUID) , toAccountId(UUID) and amount(Double).
    * Transactionally synchronized this method will enable the Transacted flow.
    * */
    @Transactional
    public synchronized void transfer(String fromAccountId, String toAccountId, Double amount) {

        // Get the Locked Account model
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(() -> new IllegalArgumentException("From Account not found"));
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow(() -> new IllegalArgumentException("To  Account not found"));
        // Amount validation checked
        if (amount < 0)
            throw new IllegalArgumentException("Negative value not allowed");

        // Balance validation check
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
