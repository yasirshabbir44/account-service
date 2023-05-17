package com.example.customer.repository;

import com.example.customer.model.Account;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {


    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    public Optional<Account> findById(String id);
}
