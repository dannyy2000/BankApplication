package com.example.MySpringBootBankApplication.dtos.AccountDtos.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class WithdrawalAccountRequest {
    private BigDecimal withdrawalAmount;
    private String accountNumber;
}
