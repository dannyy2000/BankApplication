package com.example.MySpringBootBankApplication.exception.AccountException;

public class AccountAlreadyExistException extends Exception{
    public AccountAlreadyExistException(String message) {
        super(message);
    }
}
