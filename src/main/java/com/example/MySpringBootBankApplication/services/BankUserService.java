package com.example.MySpringBootBankApplication.services;

import com.example.MySpringBootBankApplication.data.model.BankUser;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.request.BankUserAddAccountRequest;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.request.BankUserLoginRequest;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.request.BankUserSignUpRequest;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.request.BankUserUpdateDetailsRequest;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.response.BankUserAddAccountResponse;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.response.BankUserLoginResponse;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.response.BankUserSignUpResponse;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.response.BankUserUpdateDetailsResponse;
import com.example.MySpringBootBankApplication.exception.BankUserException.LoginException;
import com.example.MySpringBootBankApplication.exception.BankUserException.SignUpException;
import com.example.MySpringBootBankApplication.exception.BankUserException.UpdateDetailsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankUserService {

        BankUserSignUpResponse userSignUp (BankUserSignUpRequest bankUserSignUpRequest) throws SignUpException;
        BankUserLoginResponse userLogin(BankUserLoginRequest bankUserLoginRequest)throws LoginException;

        BankUserUpdateDetailsResponse updateDetails(BankUserUpdateDetailsRequest bankUserUpdateDetailsRequest)
                throws UpdateDetailsException;
          BankUserAddAccountResponse userAddAccount(BankUserAddAccountRequest bankUserAddAccountRequest);

        List<BankUser> findAllUsers();

        BankUser findUserByEmail(String emailAddress);
    }


