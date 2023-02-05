package com.example.MySpringBootBankApplication.exception.AccountException;

public class CreateAccountException extends RuntimeException{
    public CreateAccountException(String message) {
        super(message);
    }
}
