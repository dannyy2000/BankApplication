package com.example.MySpringBootBankApplication.exception.AccountException;



public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
