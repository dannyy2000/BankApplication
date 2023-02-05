package com.example.MySpringBootBankApplication.services;

import com.example.MySpringBootBankApplication.data.model.Account;
import com.example.MySpringBootBankApplication.data.model.AccountType;
import com.example.MySpringBootBankApplication.data.repository.BankUserRepository;
import com.example.MySpringBootBankApplication.dtos.AccountDtos.request.*;
import com.example.MySpringBootBankApplication.dtos.AccountDtos.responses.*;
//import com.example.MySpringBootBankApplication.exception.AccountException.ChangePinException;
import com.example.MySpringBootBankApplication.exception.AccountException.*;
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
//    createAccountRequest.setId("200g");
    createAccountRequest.setAccountFirstName("Akinsanya");
    createAccountRequest.setAccountLastName("Daniel");
    createAccountRequest.setAccountType(AccountType.SAVINGS);
    createAccountRequest.setPin("1890");
    createAccountRequest.setEmailAddress("dani7895fgfrr");
     createAccountResponse = accountService.createAccount(createAccountRequest);
    System.out.println(createAccountResponse.getAccountNumber());
} catch (CreateAccountException e) {
    assertNull(createAccountResponse.getAccountNumber());
}
//       createAccountRequest.setAccountNumber(account.generateAccountNumber());

//            System.out.println(createAccountResponse.getAccountNumber());



    }

    @Test
    void changePinTest(){
        ChangePinRequest changePinRequest = new ChangePinRequest();
        ChangePinResponse changePinResponse = new ChangePinResponse();
        changePinRequest.setAccountNumber("5187516502");
//        changePinRequest.setAccountNumber("2900228141");

        try{

            changePinRequest.setPin("2000");
            changePinResponse = accountService.changePin(changePinRequest);
            System.out.println(changePinResponse.getMessage());
        } catch ( AccountDoesNotExistsException e) {
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
            depositAccountRequest.setAccountNumber("5187516502");
            depositAccountResponse = accountService.deposit(depositAccountRequest);
            System.out.println(depositAccountResponse.getMessage());
        }catch (AccountDoesNotExistsException e){
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
            withdrawalAccountRequest.setAccountNumber("5187516502");
            withdrawalAccountResponse = accountService.withdrawal(withdrawalAccountRequest);
            System.out.println(withdrawalAccountResponse.getMessage());
        } catch (WithdrawalException e) {
            assertEquals(BigDecimal.valueOf(500),withdrawalAccountResponse.getBalance());
        } catch (AccountDoesNotExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void accountCanShowBalanceTest(){
        ShowBalanceRequest showBalanceRequest = new ShowBalanceRequest();
        ShowBalanceResponse balanceResponse = new ShowBalanceResponse();

        try{
            showBalanceRequest.setAccountNumber("5187516502");
            balanceResponse = accountService.showBalance(showBalanceRequest);
            System.out.println(balanceResponse.getBalance());

        } catch (AccountDoesNotExistsException e) {
            assertEquals(BigDecimal.valueOf(500),balanceResponse.getBalance());

        }

    }
    @Test
    void accountCanBeAddedTest(){
        AddAccountRequest addAccountRequest = new AddAccountRequest();
        AddAccountResponse addAccountResponse = new AddAccountResponse();

        try{
            addAccountRequest.setAccountFirstName("Tobiow");
            addAccountRequest.setAccountLastName("Akin");
            addAccountRequest.setAccountType(AccountType.CURRENT);
            addAccountRequest.setEmailAddress("Tobi789");
            addAccountRequest.setPin("1675");

            addAccountResponse = accountService.addAccount(addAccountRequest);
            System.out.println(addAccountResponse.getAccountNumber());

        } catch (AccountAlreadyExistException e) {
            throw new RuntimeException(e.getMessage());
        }
        assertEquals("Account added",addAccountResponse.getMessage());
    }
    @Test
    void transferMoneyFromOneAccountToAnotherTest(){
        TransferRequest transferRequest = new TransferRequest();
        TransferResponse transferResponse = new TransferResponse();

        try{
            transferRequest.setDepositorAccountNumber("5187516502");
            transferRequest.setRecipientAccountNumber("7011863980");
            BigDecimal withdrawalAmount = new BigDecimal("300");
            transferRequest.setTransferAmount(withdrawalAmount);
            transferResponse = accountService.transfer(transferRequest);
        } catch (TransferException | AccountDoesNotExistsException e) {
            throw new RuntimeException(e.getMessage());
        }

        assertEquals("Transfer sent successful",transferResponse.getMessage());
    }

    @Test
    void testThatAccountCanBeClosed(){
        CloseAccountRequest closeAccountRequest = new CloseAccountRequest();
        CloseAccountResponse closeAccountResponse = new CloseAccountResponse();

        try {
            closeAccountRequest.setAccountNumber("9907916230");
            closeAccountResponse = accountService.close(closeAccountRequest);
        } catch (AccountDoesNotExistsException e) {
            throw new RuntimeException(e.getMessage());
        }
        assertEquals("Account closed",closeAccountResponse.getMessage());
    }
//    @Test
//    void findall


}