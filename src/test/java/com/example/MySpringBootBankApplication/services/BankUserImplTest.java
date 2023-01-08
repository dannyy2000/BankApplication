package com.example.MySpringBootBankApplication.services;

import com.example.MySpringBootBankApplication.data.model.AccountType;
import com.example.MySpringBootBankApplication.data.model.BankUser;
import com.example.MySpringBootBankApplication.data.repository.BankUserRepository;
import com.example.MySpringBootBankApplication.dtos.AccountDtos.request.CreateAccountRequest;
import com.example.MySpringBootBankApplication.dtos.AccountDtos.responses.CreateAccountResponse;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.request.BankUserLoginRequest;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.request.BankUserSignUpRequest;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.request.BankUserUpdateDetailsRequest;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.response.BankUserLoginResponse;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.response.BankUserSignUpResponse;
import com.example.MySpringBootBankApplication.dtos.BankUserDtos.response.BankUserUpdateDetailsResponse;
import com.example.MySpringBootBankApplication.exception.AccountException.CreateAccountException;
import com.example.MySpringBootBankApplication.exception.BankUserException.LoginException;
import com.example.MySpringBootBankApplication.exception.BankUserException.SignUpException;
import com.example.MySpringBootBankApplication.exception.BankUserException.UpdateDetailsException;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BankUserImplTest {

@Autowired
private BankUserServiceImpl bankUserService;



   @Test
    void signUpUserTest(){
        BankUserSignUpRequest bankUserSignUpRequest = new BankUserSignUpRequest();
       BankUserSignUpResponse bankUserSignUpResponse = new BankUserSignUpResponse();

      try{
           bankUserSignUpRequest.setFirstName("Dani");
           bankUserSignUpRequest.setLastName("Akins");
           bankUserSignUpRequest.setAge(12);
           bankUserSignUpRequest.setAddress("sabo");
           bankUserSignUpRequest.setPhoneNumber("0909");
           bankUserSignUpRequest.setPassword("70800");
           bankUserSignUpRequest.setEmailAddress("dani7895fgfrr");
           bankUserSignUpRequest.setConfirmPassword("70800");
           bankUserSignUpResponse = bankUserService.userSignUp(bankUserSignUpRequest);
//           assertEquals("Signed up successful",bankUserSignUpResponse.getMessage());

       } catch (SignUpException e) {
         assertNull(bankUserSignUpResponse.getEmailAddress());
//          assertEquals("Signed up successful",bankUserSignUpResponse.getMessage());
           System.out.println(e.getMessage());
       }

//
   }
   @Test
   public void findUserByEmailTest(){
        BankUser bankUser = this.bankUserService.findUserByEmail("dani7895fgfrr");
       assertEquals("Bariga",bankUser.getAddress());
   }

   @Test
    void userLoginTest(){
         BankUserLoginRequest bankUserLoginRequest = new BankUserLoginRequest();
       BankUserLoginResponse bankUserLoginResponse = new BankUserLoginResponse();


           try{
               bankUserLoginRequest.setEmailAddress("dani7895fgfrr");
               bankUserLoginRequest.setPassword("70800");
               bankUserLoginResponse = this.bankUserService.userLogin(bankUserLoginRequest);
               System.out.println(bankUserLoginResponse.getMessage());

           } catch (  LoginException e) {
               assertNotNull(bankUserLoginResponse.getEmailAddress());

           }

   }

   @Test
    void userUpdateDetailsTest(){
         BankUserUpdateDetailsRequest bankUserUpdateDetailsRequest = new BankUserUpdateDetailsRequest();
       BankUserUpdateDetailsResponse bankUserUpdateDetailsResponse = new BankUserUpdateDetailsResponse();
       bankUserUpdateDetailsRequest.setEmailAddress("dani7895fgfrr");

       try{

           bankUserUpdateDetailsRequest.setFirstName("Oluwatomiwa");
           bankUserUpdateDetailsRequest.setLastName("Akinsanya");
           bankUserUpdateDetailsRequest.setAddress("Bariga");
           bankUserUpdateDetailsRequest.setPhoneNumber("0701");
           bankUserUpdateDetailsResponse = bankUserService.updateDetails(bankUserUpdateDetailsRequest);
           System.out.println(bankUserUpdateDetailsResponse.getMessage());
       } catch ( UpdateDetailsException e) {
           assertNotNull(bankUserUpdateDetailsResponse.getEmailAddress());
       }

   }



}

