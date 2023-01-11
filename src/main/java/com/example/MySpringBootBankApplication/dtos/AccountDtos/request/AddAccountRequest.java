package com.example.MySpringBootBankApplication.dtos.AccountDtos.request;

import com.example.MySpringBootBankApplication.data.model.AccountType;
import lombok.Data;

@Data
public class AddAccountRequest {
    private String accountFirstName;
    private String accountLastName;
    private AccountType accountType;
    private String emailAddress;
    private String pin;
}
