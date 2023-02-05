package com.example.MySpringBootBankApplication.exception;

import com.example.MySpringBootBankApplication.exception.AccountException.*;
//import com.example.MySpringBootBankApplication.exception.AccountException.AccountNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import utils.ApiResponse;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CreateAccountException.class)
    public ResponseEntity<ApiResponse> createAccountException(
            CreateAccountException createAccountException, HttpServletRequest httpServletRequest
    ){
        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .statusCode(HttpStatus.CONFLICT.value())
                .path(httpServletRequest.getRequestURI())
                .data(createAccountException.getMessage())
                .isSuccessful(false)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountDoesNotExistsException.class)
    public ResponseEntity<ApiResponse> accountNotFoundException(
            AccountDoesNotExistsException accountNotFoundException, HttpServletRequest httpServletRequest
    ){
        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .statusCode(HttpStatus.CONFLICT.value())
                .path(httpServletRequest.getRequestURI())
                .data(accountNotFoundException.getMessage())
                .isSuccessful(false)
                .build();

     return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepositException.class)
    public ResponseEntity<ApiResponse> depositException(
            DepositException depositException,HttpServletRequest httpServletRequest
    ){
        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .statusCode(HttpStatus.CONFLICT.value())
                .path(httpServletRequest.getRequestURI())
                .data(depositException.getMessage())
                .isSuccessful(false)
                .build();

        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WithdrawalException.class)
    public ResponseEntity<ApiResponse> withdrawException(
            WithdrawalException withdrawalException,HttpServletRequest httpServletRequest
    ){
        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .statusCode(HttpStatus.CONFLICT.value())
                .path(httpServletRequest.getRequestURI())
                .data(withdrawalException.getMessage())
                .isSuccessful(false)
                .build();

        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountAlreadyExistException.class)
    public ResponseEntity<ApiResponse> accountAlreadyExistsException(
            AccountAlreadyExistException accountAlreadyExistException,
            HttpServletRequest httpServletRequest

    ){
        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .statusCode(HttpStatus.CONFLICT.value())
                .path(httpServletRequest.getRequestURI())
                .data(accountAlreadyExistException.getMessage())
                .isSuccessful(false)
                .build();

        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<ApiResponse> transferException(
            TransferException transferException,HttpServletRequest httpServletRequest
    ){
        ApiResponse apiResponse = ApiResponse.builder()
                .time(ZonedDateTime.now())
                .statusCode(HttpStatus.CONFLICT.value())
                .path(httpServletRequest.getRequestURI())
                .data(transferException.getMessage())
                .isSuccessful(false)
                .build();
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
}
