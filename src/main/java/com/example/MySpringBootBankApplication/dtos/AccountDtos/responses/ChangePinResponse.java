package com.example.MySpringBootBankApplication.dtos.AccountDtos.responses;

import lombok.Data;

@Data
public class ChangePinResponse {
    private String accountNumber;
    private String pin;
    private String message;
}
