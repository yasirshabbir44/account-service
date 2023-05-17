package com.example.customer.controller;

import com.example.customer.dto.AccountDTO;
import com.example.customer.dto.AmountDTO;
import com.example.customer.service.AccountService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api("Transfer services")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/account")
public class AccountController {

    private final AccountService accountService;


    @GetMapping
    public List<AccountDTO> getAccounts(@RequestParam(required = false) Integer pageNumber,
                                        @RequestParam(required = false) Integer pageSize) {
        return accountService.getAccounts(pageNumber, pageSize);
    }

    @ApiOperation(value = "Transfer money from an account to other account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "Internal Error.")
    })
    @PostMapping(value = "transfer/{fromBankAccountId}/{toBankAccountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void transfer(@ApiParam(value = "The ID of the from bank account") @PathVariable(name = "fromBankAccountId") String fromBankAccountId,
                         @ApiParam(value = "The ID of the to bank account") @PathVariable(name = "toBankAccountId") String toBankAccountId,
                         @ApiParam(value = "The amount of the withdraw transaction") @RequestBody @Valid AmountDTO amountDto) {
        log.info("/{}/{}/{} called with amount: {}", fromBankAccountId, toBankAccountId, amountDto);

        accountService.transfer(fromBankAccountId, toBankAccountId, amountDto.getAmount());
    }
}
