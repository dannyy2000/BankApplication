package com.example.MySpringBootBankApplication.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.security.SecureRandom;
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Document
public class Account {
    @Id
    @NonNull
    private String accountNumber;
    @NonNull
    private String pin;
    @NonNull

    private String emailAddress;
    @NonNull
    private String accountFirstName;
    @NonNull
    private String accountLastName;
    @NonNull
    private AccountType accountType;

    @NonNull
    private BigDecimal balance;
//

//    BigDecimal initialBalance = new BigDecimal(0.0);

    public BigDecimal getBalancee(){
        this.balance = new BigDecimal(0.0);
        return this.balance;
    }


//    public String generateAccNumber(){
//        SecureRandom random = new SecureRandom();
//        long accountNumber = Math.abs(random.nextLong());
//        accountNumber = accountNumber % 10000000000L;
//        return String.valueOf(accountNumber);
//    }

    public String generateAccountNumber(){
        SecureRandom secureRandom = new SecureRandom();
        long accountNumber = Math.abs(secureRandom.nextLong());
        this.accountNumber = String.valueOf(accountNumber % 10000000000L);
        return this.accountNumber;
    }
}
