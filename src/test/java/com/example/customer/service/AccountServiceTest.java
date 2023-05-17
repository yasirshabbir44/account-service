package com.example.customer.service;

import com.example.customer.dto.AccountDTO;
import com.example.customer.model.Account;
import com.example.customer.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public class AccountServiceTest {


    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testGetAll() {
        String accountIdOne = UUID.randomUUID().toString();
        String accountIdTwo = UUID.randomUUID().toString();

        Account account1 =Account.builder().id(accountIdOne).balance(100.0).name("Yasir").build();
        Account account2 = Account.builder().id(accountIdTwo).balance(100.0).name("Samir").build();

        Mockito.when(accountRepository.findAll())
                .thenReturn(List.of(account1,account2));

        List<AccountDTO> accounts = accountService.getAccounts(null,null);

        Assert.assertNotNull(accounts);
        Assert.assertEquals(accounts.size(),2);
        Assert.assertEquals(accounts.get(0).getBalance(),account1.getBalance());
        Assert.assertEquals(accounts.get(0).getId(),account1.getId());
        Assert.assertEquals(accounts.get(0).getName(),account1.getName());
    }


    @Test
    public void testGetAllWithPagination() {
        String accountIdOne = UUID.randomUUID().toString();
        String accountIdTwo = UUID.randomUUID().toString();

        Account account1 =Account.builder().id(accountIdOne).balance(100.0).name("Yasir").build();
        Account account2 = Account.builder().id(accountIdTwo).balance(100.0).name("Samir").build();

        Mockito.when(accountRepository.findAll(PageRequest.of(0, 10)))
                .thenReturn( new PageImpl<>(List.of(account1,account2)));


        List<AccountDTO> accounts = accountService.getAccounts(0 ,10);

        Assert.assertNotNull(accounts);
        Assert.assertEquals(accounts.size(),2);
        Assert.assertEquals(accounts.get(0).getBalance(),account1.getBalance());
        Assert.assertEquals(accounts.get(0).getId(),account1.getId());
        Assert.assertEquals(accounts.get(0).getName(),account1.getName());
    }

}
