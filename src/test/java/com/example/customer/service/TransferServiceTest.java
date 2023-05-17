package com.example.customer.service;

import com.example.customer.exception.InsufficientBalanceException;
import com.example.customer.model.Account;
import com.example.customer.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

public class TransferServiceTest {


    @InjectMocks
    private TransferService transferService;

    @Mock
    private AccountRepository accountRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testTransfer() {
        String accountIdOne = UUID.randomUUID().toString();
        String accountIdTwo = UUID.randomUUID().toString();

        Optional<Account> account1 = Optional.ofNullable(Account.builder().id(accountIdOne).balance(100.0).name("Yasir").build());
        Optional<Account> account2 = Optional.ofNullable(Account.builder().id(accountIdTwo).balance(100.0).name("Samir").build());

        Mockito.when(accountRepository.findById(accountIdOne))
                .thenReturn(account1);

        Mockito.when(accountRepository.findById(accountIdTwo))
                .thenReturn(account2);

        transferService.transfer(accountIdOne,accountIdTwo,5.0);

        Mockito.verify(accountRepository, Mockito.times(1)).save(account1.get());
        Mockito.verify(accountRepository, Mockito.times(1)).save(account2.get());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testWithNegativeValueRuntimeException() {

        String accountIdOne = UUID.randomUUID().toString();
        String accountIdTwo = UUID.randomUUID().toString();

        Optional<Account> account1 = Optional.ofNullable(Account.builder().id(accountIdOne).balance(100.0).name("Yasir").build());
        Optional<Account> account2 = Optional.ofNullable(Account.builder().id(accountIdTwo).balance(100.0).name("Samir").build());

        Mockito.when(accountRepository.findById(accountIdOne))
                .thenReturn(account1);

        Mockito.when(accountRepository.findById(accountIdTwo))
                .thenReturn(account2);

        transferService.transfer(accountIdOne,accountIdOne,-55.0);
    }


    @Test(expected = InsufficientBalanceException.class)
    public void testWithInsufficientBalanceException() {

        String accountIdOne = UUID.randomUUID().toString();
        String accountIdTwo = UUID.randomUUID().toString();

        Optional<Account> account1 = Optional.ofNullable(Account.builder().id(accountIdOne).balance(100.0).name("Yasir").build());
        Optional<Account> account2 = Optional.ofNullable(Account.builder().id(accountIdTwo).balance(100.0).name("Samir").build());

        Mockito.when(accountRepository.findById(accountIdOne))
                .thenReturn(account1);

        Mockito.when(accountRepository.findById(accountIdTwo))
                .thenReturn(account2);

        transferService.transfer(accountIdOne,accountIdOne,500.0);
    }



}
