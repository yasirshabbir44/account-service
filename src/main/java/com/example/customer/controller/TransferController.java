package com.example.customer.controller;

import com.example.customer.dto.AmountDTO;
import com.example.customer.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@Tag(name = "Transfer Controller")
@RequestMapping(TransferController.SERVICE_PATH)
public class TransferController {

    public static final String SERVICE_PATH = "api/transfer";


    private final TransferService transferService;

    @Operation(summary = "Transfer money from an account to other account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "500", description = "Internal Error.")
    })
    @PostMapping(value = "/{fromBankAccountId}/{toBankAccountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void transfer(@Parameter(description = "The ID of the from bank account") @PathVariable(name = "fromBankAccountId") String fromBankAccountId,
                         @Parameter(description = "The ID of the to bank account") @PathVariable(name = "toBankAccountId") String toBankAccountId,
                         @Parameter(description = "The amount of the withdraw transaction") @RequestBody @Valid AmountDTO amountDto) {
        log.info("/{}/{}/{} called with amount: {}", fromBankAccountId, toBankAccountId, amountDto);

        transferService.transfer(fromBankAccountId, toBankAccountId, amountDto.getAmount());
    }
}
