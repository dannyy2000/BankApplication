package com.example.MySpringBootBankApplication.services;

import com.example.MySpringBootBankApplication.data.model.Account;
import com.example.MySpringBootBankApplication.data.model.AccountType;
import com.example.MySpringBootBankApplication.data.repository.BankUserRepository;
import com.example.MySpringBootBankApplication.dtos.AccountDtos.request.*;
import com.example.MySpringBootBankApplication.dtos.AccountDtos.responses.*;
//import com.example.MySpringBootBankApplication.exception.AccountException.ChangePinException;
import com.example.MySpringBootBankApplication.exception.AccountException.CreateAccountException;
import com.example.MySpringBootBankApplication.exception.AccountException.DepositException;
import com.example.MySpringBootBankApplication.exception.AccountException.WithdrawalException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.AccountNotFoundException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    private AccountServiceImpl accountService;
    private BankUserRepository bankUserRepository;

    @Test
    void testThatAccountCanBeCreated(){
          CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        CreateAccountResponse createAccountResponse = new CreateAccountResponse();
try {
    createAccountRequest.setId("200g");
    createAccountRequest.setAccountFirstName("Akinsanya");
    createAccountRequest.setAccountLastName("Daniel");
    createAccountRequest.setAccountType(AccountType.SAVINGS);
    createAccountRequest.setPin("1890");
    createAccountRequest.setEmailAddress("dani7895fgfrr");
     createAccountResponse = accountService.createAccount(createAccountRequest);
    System.out.println(createAccountResponse.getAccountNumber());
} catch (CreateAccountException e) {
    assertNotNull(createAccountResponse.getAccountNumber());
}
//       createAccountRequest.setAccountNumber(account.generateAccountNumber());

//            System.out.println(createAccountResponse.getAccountNumber());



    }

    @Test
    void changePinTest(){
        ChangePinRequest changePinRequest = new ChangePinRequest();
        ChangePinResponse changePinResponse = new ChangePinResponse();
        changePinRequest.setAccountNumber("9907916230");
//        changePinRequest.setAccountNumber("2900228141");

        try{

            changePinRequest.setPin("2000");
            changePinResponse = accountService.changePin(changePinRequest);
            System.out.println(changePinResponse.getMessage());
        } catch (AccountNotFoundException e) {
            assertEquals("Pin changed successfully",changePinResponse.getMessage());
            System.out.println(changePinResponse.getMessage());
        }

    }
    @Test
    void depositAndIncreaseBalanceTest() {
        BigDecimal depositAmount = new BigDecimal("1000");
        DepositAccountRequest depositAccountRequest = new DepositAccountRequest();
        DepositAccountResponse depositAccountResponse = new DepositAccountResponse();
        try {
            depositAccountRequest.setDepositAmount(depositAmount);
            depositAccountRequest.setAccountNumber("9907916230");
            depositAccountResponse = accountService.deposit(depositAccountRequest);
            System.out.println(depositAccountResponse.getMessage());
        }catch (AccountNotFoundException e){
            assertEquals(depositAmount,depositAccountResponse.getBalance());
        } catch (DepositException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void WithdrawalAndBalanceDecreaseTest(){
        BigDecimal withdrawalAmount = new BigDecimal("500");
        WithdrawalAccountRequest withdrawalAccountRequest = new WithdrawalAccountRequest();
        WithdrawalAccountResponse withdrawalAccountResponse = new WithdrawalAccountResponse();

        try{
            withdrawalAccountRequest.setWithdrawalAmount(withdrawalAmount);
            withdrawalAccountRequest.setAccountNumber("9907916230");
            withdrawalAccountResponse = accountService.withdrawal(withdrawalAccountRequest);
            System.out.println(withdrawalAccountResponse.getMessage());
        } catch (WithdrawalException e) {
            assertEquals(BigDecimal.valueOf(500),withdrawalAccountResponse.getBalance());
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void accountCanShowBalanceTest(){
        ShowBalanceRequest showBalanceRequest = new ShowBalanceRequest();
        ShowBalanceResponse balanceResponse = new ShowBalanceResponse();

        try{
            showBalanceRequest.setAccountNumber("9907916230");
            balanceResponse = accountService.showBalance(showBalanceRequest);
            System.out.println(balanceResponse.getBalance());

        } catch (AccountNotFoundException e) {
            assertEquals(BigDecimal.valueOf(500),balanceResponse.getBalance());

        }
    }


}