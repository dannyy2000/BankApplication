package com.example.MySpringBootBankApplication.dtos.AccountDtos.responses;

import com.example.MySpringBootBankApplication.data.model.AccountType;
import lombok.Data;

@Data
public class ViewAccountResponse {
    private String firstName;
    private String lastName;
    private AccountType accountType;
    private String emailAddress;
    private String accountNumber;
    private String pin;
}
