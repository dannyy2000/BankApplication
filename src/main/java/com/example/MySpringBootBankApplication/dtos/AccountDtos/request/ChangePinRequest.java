package com.example.MySpringBootBankApplication.dtos.AccountDtos.request;

import lombok.Data;

@Data
public class ChangePinRequest {
    private String accountNumber;

    private String pin;
}
