package com.example.MySpringBootBankApplication.dtos.AccountDtos.responses;

import lombok.Data;

@Data
public class AddAccountResponse {
    private String accountNumber;
    private String message;
}
