package com.example.MySpringBootBankApplication.dtos.AccountDtos.request;

import com.example.MySpringBootBankApplication.data.model.AccountType;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CreateAccountRequest {
    private String accountFirstName;
    private String accountLastName;
    private String pin;
    private String accountNumber;
//    private String id;
    private String emailAddress;
    private AccountType accountType;
    private BigDecimal balance;
}
