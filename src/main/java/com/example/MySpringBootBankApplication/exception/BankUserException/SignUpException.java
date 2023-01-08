package com.example.MySpringBootBankApplication.exception.BankUserException;

public class SignUpException extends Exception{
    public SignUpException(String message) {
        super(message);
    }
}
