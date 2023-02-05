package com.example.MySpringBootBankApplication.dtos.AccountDtos.responses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferResponse {
    private String message;
    private BigDecimal depositorBalance;
    private BigDecimal recipientBalance;
}
