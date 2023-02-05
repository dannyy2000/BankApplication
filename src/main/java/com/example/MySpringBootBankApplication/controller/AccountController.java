package com.example.MySpringBootBankApplication.controller;

import com.example.MySpringBootBankApplication.dtos.AccountDtos.request.*;
import com.example.MySpringBootBankApplication.dtos.AccountDtos.responses.*;
import com.example.MySpringBootBankApplication.exception.AccountException.*;
import com.example.MySpringBootBankApplication.services.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.ApiResponse;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    private ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest createAccountRequest,
                                            HttpServletRequest httpServletRequest) {
        CreateAccountResponse createAccountResponse = accountService.createAccount(createAccountRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .path(httpServletRequest.getRequestURI())
                .data(createAccountResponse.getMessage())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/pin")
    private ResponseEntity<?> changePin(@RequestBody ChangePinRequest changePinRequest,
                                        HttpServletRequest httpServletRequest) throws AccountDoesNotExistsException {
        ChangePinResponse changePinResponse = accountService.changePin(changePinRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .path(httpServletRequest.getRequestURI())
                .data(changePinResponse)
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/emailAddress")
    public ResponseEntity<?> viewAccount(@RequestBody ViewAccountRequest viewAccountRequest, HttpServletRequest httpServletRequest) throws AccountDoesNotExistsException {
        ViewAccountResponse viewAccountResponse = accountService.viewAccount(viewAccountRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .path(httpServletRequest.getRequestURI())
                .data(viewAccountResponse)
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.FOUND);

    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositAccountRequest depositAccountRequest, HttpServletRequest
            httpServletRequest) throws AccountDoesNotExistsException, DepositException {
        DepositAccountResponse depositAccountResponse = accountService.deposit(depositAccountRequest);

        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .path(httpServletRequest.getRequestURI())
                .data(depositAccountResponse)
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawalAccountRequest withdrawalAccountRequest,
                                      HttpServletRequest httpServletRequest) throws WithdrawalException, AccountDoesNotExistsException {

        WithdrawalAccountResponse withdrawalAccountResponse = accountService.withdrawal(withdrawalAccountRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .data(withdrawalAccountResponse)
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("Add")
    public ResponseEntity<?> addAccount(@RequestBody AddAccountRequest addAccountRequest,
                                        HttpServletRequest httpServletRequest) throws AccountAlreadyExistException {
        AddAccountResponse addAccountResponse = accountService.addAccount(addAccountRequest);

        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .data(addAccountResponse)
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @PostMapping("transfer")
    public ResponseEntity<?>transfer(@RequestBody TransferRequest transferRequest,
                                     HttpServletRequest httpServletRequest) throws TransferException, AccountDoesNotExistsException {
        TransferResponse transferResponse = accountService.transfer(transferRequest);

        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .data(transferResponse)
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("balance")
    public ResponseEntity<?> balance(@RequestBody ShowBalanceRequest showBalanceRequest,
                                     HttpServletRequest httpServletRequest) throws AccountDoesNotExistsException {
        ShowBalanceResponse showBalanceResponse = accountService.showBalance(showBalanceRequest);

        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .data(showBalanceResponse)
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);


    }


}