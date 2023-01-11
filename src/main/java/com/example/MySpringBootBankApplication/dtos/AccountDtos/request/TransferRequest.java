package com.example.MySpringBootBankApplication.dtos.AccountDtos.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class TransferRequest {
    private String depositorAccountNumber;
    private BigDecimal transferAmount;
    private String recipientAccountNumber;
}
