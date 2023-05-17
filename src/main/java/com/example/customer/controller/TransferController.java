package com.example.customer.controller;

import com.example.customer.dto.AmountDTO;
import com.example.customer.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(TransferController.SERVICE_PATH )
public class TransferController {

    public static final String SERVICE_PATH = "api/transfer";


    private final AccountService accountService;

    @ApiOperation(value = "Transfer money from an account to other account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "Internal Error.")
    })
    @PostMapping(value = "/{fromBankAccountId}/{toBankAccountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void transfer(@ApiParam(value = "The ID of the from bank account") @PathVariable(name = "fromBankAccountId") String fromBankAccountId,
                         @ApiParam(value = "The ID of the to bank account") @PathVariable(name = "toBankAccountId") String toBankAccountId,
                         @ApiParam(value = "The amount of the withdraw transaction") @RequestBody @Valid AmountDTO amountDto) {
        log.info("/{}/{}/{} called with amount: {}", fromBankAccountId, toBankAccountId, amountDto);

        accountService.transfer(fromBankAccountId, toBankAccountId, amountDto.getAmount());
    }
}
