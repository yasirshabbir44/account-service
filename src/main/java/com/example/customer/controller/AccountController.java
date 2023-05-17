package com.example.customer.controller;

import com.example.customer.dto.AccountDTO;
import com.example.customer.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.customer.controller.AccountController.SERVICE_PATH;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(SERVICE_PATH)
public class AccountController {

    public static final String SERVICE_PATH = "api/account";

    private final AccountService accountService;


    /*
     * It's return the all Account residing inside system, if you want pagination
     * then simple pass the pageNumber and pageSize in Request Param it, will return result in
     * Pagination wise.
     * */
    @GetMapping
    public List<AccountDTO> getAccounts(@RequestParam(required = false) Integer pageNumber,
                                        @RequestParam(required = false) Integer pageSize) {
        return accountService.getAccounts(pageNumber, pageSize);
    }


}
