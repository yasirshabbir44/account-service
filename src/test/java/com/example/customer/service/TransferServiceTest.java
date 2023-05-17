package com.example.customer.service;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class TransferServiceTest {

    @InjectMocks
    private TransferService transferService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }



}
