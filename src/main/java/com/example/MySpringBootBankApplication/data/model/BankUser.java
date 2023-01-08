package com.example.MySpringBootBankApplication.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


    @Document
    @Data
    @NoArgsConstructor
    @RequiredArgsConstructor
    public class BankUser {

        @Id
        @NonNull
        private String emailAddress;
        @NonNull
        private String firstName;
        @NonNull
        private String lastName;
        @NonNull
        private int age;
        @NonNull
        private String phoneNumber;
        @NonNull
        private String address;
        @NonNull
        private String password;
        @DBRef
        private List<Account> accounts = new ArrayList<>();

    }
