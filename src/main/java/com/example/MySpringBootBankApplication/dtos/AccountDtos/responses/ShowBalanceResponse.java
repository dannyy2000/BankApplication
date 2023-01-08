package com.example.MySpringBootBankApplication.dtos.AccountDtos.responses;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ShowBalanceResponse {
    private String accountNumber;
    private BigDecimal balance;
}
