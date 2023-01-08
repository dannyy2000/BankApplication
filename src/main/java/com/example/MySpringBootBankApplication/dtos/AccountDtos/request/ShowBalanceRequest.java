package com.example.MySpringBootBankApplication.dtos.AccountDtos.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ShowBalanceRequest {
    private String accountNumber;
    private BigDecimal balance;
}
