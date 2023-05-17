package com.example.customer.service;

import com.example.customer.dto.AccountDTO;
import com.example.customer.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

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
