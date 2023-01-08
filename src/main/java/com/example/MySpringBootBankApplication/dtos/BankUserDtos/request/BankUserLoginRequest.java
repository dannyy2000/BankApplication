package com.example.MySpringBootBankApplication.dtos.BankUserDtos.request;

import lombok.Data;

@Data
public class BankUserLoginRequest {

    private String emailAddress;
    private String password;
}
