package com.example.MySpringBootBankApplication.dtos.AccountDtos.responses;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class WithdrawalAccountResponse {
    private BigDecimal balance;
    private String message;
}
