package com.example.MySpringBootBankApplication.dtos.AccountDtos.responses;

import lombok.Data;

@Data
public class CreateAccountResponse {
    private String AccountNumber;
    private String message;
}
