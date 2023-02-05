package com.example.MySpringBootBankApplication.data.repository;

import com.example.MySpringBootBankApplication.data.model.BankUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankUserRepository extends MongoRepository<BankUser,String> {
  Optional <BankUser> findBankUserByEmailAddress(String email);
}
