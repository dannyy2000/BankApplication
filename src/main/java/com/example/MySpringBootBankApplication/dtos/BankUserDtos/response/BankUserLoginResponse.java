package com.example.MySpringBootBankApplication.dtos.BankUserDtos.response;

import lombok.Data;

@Data
public class BankUserLoginResponse {
    private String emailAddress;
    private String message;
}
