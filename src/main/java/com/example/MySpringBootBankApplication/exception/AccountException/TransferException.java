package com.example.MySpringBootBankApplication.exception.AccountException;

public class TransferException extends Exception{
    public TransferException(String message) {
        super(message);
    }
}
