package com.example.customer.controller;

import com.example.customer.dto.AmountDTO;
import com.example.customer.service.AccountService;
import com.example.customer.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class TransferControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransferService transferService;

    @InjectMocks
    private TransferController transferController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transferController)
                .setControllerAdvice(ControllerAdvice.class).build();
    }



    @Test
    public void testTransfer() throws Exception {
        String fromBankAccount = "3d253e29-8785-464f-8fa0-9e4b57699db9";
        String toBankAccount = "17f904c1-806f-4252-9103-74e7a5d3e340";
        AmountDTO amountDto = AmountDTO.builder().amount(55.0).build();

        String servicePath = String.format("/%s/%s/%s",
                TransferController.SERVICE_PATH,
                fromBankAccount,
                toBankAccount);
        mockMvc.perform(post(
                        servicePath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(amountDto)))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
