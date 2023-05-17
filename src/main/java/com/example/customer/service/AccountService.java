package com.example.customer.service;

import com.example.customer.Component.AccountComponent;
import com.example.customer.dto.AccountDTO;
import com.example.customer.model.Account;
import com.example.customer.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public synchronized void transfer(String from, String to, Double amount) {


        Account fromAccount = accountRepository.findById(from).orElseThrow(() -> new IllegalArgumentException("From Account not found"));
        Account toAccount = accountRepository.findById(to).orElseThrow(() -> new IllegalArgumentException("To  Account not found"));
        // synchronizing from this point, since balances are checked
        if (amount < 0)
            throw new IllegalArgumentException("Negative value not allowed");


        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficent balance");
        }

        toAccount.setBalance(toAccount.getBalance() + amount);
        fromAccount.setBalance(fromAccount.getBalance() - amount);

        accountRepository.save(toAccount);
        accountRepository.save(fromAccount);
    }


    public List<AccountDTO> getAccounts(Integer pageNumber, Integer pageSize) {


        Pageable pageable = null;
        if (Objects.nonNull(pageNumber) && Objects.nonNull(pageSize)) {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return Optional.ofNullable(pageable)
                .map(p -> accountRepository.findAll(p).stream().parallel())
                .orElseGet(() -> accountRepository.findAll().parallelStream())
                .map(account -> new AccountDTO(account.getId(), account.getName(), account.getBalance()))
                .toList();
    }
}
