package com.example.MySpringBootBankApplication.dtos.BankUserDtos.request;

import lombok.Data;

@Data
public class BankUserUpdateDetailsRequest {
    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String emailAddress;
    private String phoneNumber;
}
