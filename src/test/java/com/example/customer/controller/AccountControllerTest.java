package com.example.customer.controller;

import com.example.customer.dto.AccountDTO;
import com.example.customer.service.AccountService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest {


    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(ControllerAdvice.class)
                .build();
    }

    @Test
    public void testGetAllBalance() throws Exception {


        List<AccountDTO> bankAccountList = Collections.singletonList(AccountDTO.builder().id("3d253e29-8785-464f-8fa0-9e4b57699db9").name("Yasir").balance(500.0).build());

        Mockito.when(accountService.getAccounts(null, null)).thenReturn(bankAccountList);

        String servicePath = String.format("/%s",
                AccountController.SERVICE_PATH);

        MvcResult mvcResult = mockMvc
                .perform(get(servicePath)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<AccountDTO> accountDTOS = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<AccountDTO>>() {
        });

        Assert.assertFalse(accountDTOS.isEmpty());
        accountDTOS.forEach(accountDTO -> {
            Assert.assertNotNull(accountDTO.getBalance());
            Assert.assertNotNull(accountDTO.getId());
            Assert.assertNotNull(accountDTO.getName());
        });

    }

}
