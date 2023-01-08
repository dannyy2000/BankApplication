package com.example.MySpringBootBankApplication.dtos.AccountDtos.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class DepositAccountRequest {
    private BigDecimal depositAmount;
    private String accountNumber;
}
