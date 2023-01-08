package com.example.MySpringBootBankApplication.dtos.AccountDtos.responses;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class DepositAccountResponse {
    private BigDecimal balance;
    private String message;
}
