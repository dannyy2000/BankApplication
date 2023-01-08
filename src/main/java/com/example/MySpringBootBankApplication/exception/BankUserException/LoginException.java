package com.example.MySpringBootBankApplication.exception.BankUserException;

public class LoginException extends Exception{
    public LoginException(String message) {
        super(message);
    }
}
