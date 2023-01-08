package com.example.MySpringBootBankApplication.services;

import com.example.MySpringBootBankApplication.data.model.BankUser;
import com.example.MySpringBootBankApplication.data.repository.BankUserRepository;
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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankUserServiceImpl implements BankUserService {
    @Autowired
    private BankUserRepository bankUserRepository;


    @Override
    public BankUserSignUpResponse userSignUp( BankUserSignUpRequest bankUserSignUpRequest) throws SignUpException {

          BankUserSignUpResponse bankUserSignUpResponse = new BankUserSignUpResponse();
          BankUser bankUser = new BankUser();

        if (validatePasswordMatch(bankUserSignUpRequest)) {
            if (bankUserRepository.existsById(bankUserSignUpRequest.getEmailAddress())) {
                bankUser.setFirstName(bankUserSignUpRequest.getFirstName());
                bankUser.setLastName(bankUserSignUpRequest.getLastName());
                bankUser.setAddress(bankUserSignUpRequest.getAddress());
                bankUser.setAge(bankUserSignUpRequest.getAge());
                bankUser.setEmailAddress(bankUserSignUpRequest.getEmailAddress());
                bankUser.setPhoneNumber(bankUserSignUpRequest.getPhoneNumber());
                bankUser.setPassword(bankUserSignUpRequest.getPassword());


                bankUserRepository.save(bankUser);
                bankUserSignUpResponse.setEmailAddress(bankUser.getEmailAddress());
                bankUserSignUpResponse.setMessage("Signed up successful");

                return bankUserSignUpResponse;


            } else throw new SignUpException("Email already exists");

        } else {
            throw new SignUpException("Password doesnt match");
        }

    }


    private boolean validatePasswordMatch( BankUserSignUpRequest bankUserSignUpRequest) {
        return bankUserSignUpRequest.getPassword().equals(bankUserSignUpRequest.getConfirmPassword());

    }


    @Override
    public BankUserLoginResponse userLogin(BankUserLoginRequest bankUserLoginRequest) throws LoginException {
         BankUserLoginResponse bankUserLoginResponse = new BankUserLoginResponse();
          BankUser user = findUserByEmail(bankUserLoginRequest.getEmailAddress());
        if (user != null) {
            if (bankUserLoginRequest.getPassword().equals(user.getPassword())) {
                bankUserLoginResponse.setEmailAddress(user.getEmailAddress());
                bankUserLoginResponse.setMessage("Login successful");
                return bankUserLoginResponse;
            } else throw new LoginException("Password is incorrect");
        }
        throw new LoginException("User not found");
    }

    @Override
    public BankUserUpdateDetailsResponse updateDetails(BankUserUpdateDetailsRequest bankUserUpdateDetailsRequest) throws UpdateDetailsException {
          BankUserUpdateDetailsResponse bankUserUpdateDetailsResponse = new BankUserUpdateDetailsResponse();
          BankUser user = findUserByEmail(bankUserUpdateDetailsRequest.getEmailAddress());
        if(user != null){
            user.setFirstName(bankUserUpdateDetailsRequest.getFirstName());
            user.setLastName(bankUserUpdateDetailsRequest.getLastName());
             user.setPhoneNumber(bankUserUpdateDetailsRequest.getPhoneNumber());
            user.setAddress(bankUserUpdateDetailsRequest.getAddress());
            bankUserRepository.save(user);

        }else throw new UpdateDetailsException("User does not exist");

        bankUserUpdateDetailsResponse.setEmailAddress(user.getEmailAddress());
        bankUserUpdateDetailsResponse.setMessage("Update successful");

        return bankUserUpdateDetailsResponse;
    }


    @Override
    public BankUserAddAccountResponse userAddAccount( BankUserAddAccountRequest bankUserAddAccountRequest) {
        return null;
    }

    @Override
    public List<BankUser> findAllUsers() {
       return bankUserRepository.findAll();
    }

    @Override
    public BankUser findUserByEmail( String emailAddress) {
        return bankUserRepository.findAll().stream().filter(b->b.getEmailAddress().equals(emailAddress)).
                findFirst().get();
    }
}
