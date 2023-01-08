package com.example.MySpringBootBankApplication.dtos.BankUserDtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class BankUserSignUpRequest {

    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private String confirmPassword;
}
