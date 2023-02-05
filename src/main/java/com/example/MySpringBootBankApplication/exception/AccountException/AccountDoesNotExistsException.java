package com.example.MySpringBootBankApplication.exception.AccountException;



public class AccountDoesNotExistsException extends Exception {
    public AccountDoesNotExistsException(String message) {
        super(message);
    }
}

