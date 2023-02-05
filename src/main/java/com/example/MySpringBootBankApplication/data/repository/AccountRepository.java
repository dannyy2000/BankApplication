package com.example.MySpringBootBankApplication.data.repository;

import com.example.MySpringBootBankApplication.data.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends MongoRepository<Account,String> {

   Optional<Account>findByAccountNumber(String accountNumber);
   Optional<Account> findByEmailAddress(String emailAddress);
}
