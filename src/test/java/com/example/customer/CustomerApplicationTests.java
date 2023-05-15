package com.example.customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
class CustomerApplicationTests {

    @SpyBean
    ApplicationRunner applicationRunnerTaskExecutor;

    @Test
    void contextLoads() {
    }


    /*
     * Test case that verify ApplicationRunner should run only once time
     * */
    @Test
    void whenContextLoads_thenRunnersRun() throws Exception {
        verify(applicationRunnerTaskExecutor, times(1)).run(any());
    }

}
