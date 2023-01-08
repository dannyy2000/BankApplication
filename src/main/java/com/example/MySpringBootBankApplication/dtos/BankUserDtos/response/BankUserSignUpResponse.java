package com.example.MySpringBootBankApplication.dtos.BankUserDtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class BankUserSignUpResponse {
    private String emailAddress;

    private String message;
    private String userId;
}
