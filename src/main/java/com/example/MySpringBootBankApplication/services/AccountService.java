package com.example.MySpringBootBankApplication.services;

import com.example.MySpringBootBankApplication.data.model.Account;
import com.example.MySpringBootBankApplication.dtos.AccountDtos.request.*;
import com.example.MySpringBootBankApplication.dtos.AccountDtos.responses.*;
import com.example.MySpringBootBankApplication.exception.AccountException.*;
import org.springframework.stereotype.Service;

//import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public interface AccountService {
    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) throws CreateAccountException;
    ChangePinResponse changePin(ChangePinRequest changePinRequest) throws AccountDoesNotExistsException;

    DepositAccountResponse deposit(DepositAccountRequest depositAccountRequest) throws DepositException, AccountDoesNotExistsException;
    WithdrawalAccountResponse withdrawal(WithdrawalAccountRequest withdrawalAccountRequest)
            throws AccountDoesNotExistsException, WithdrawalException;

    TransferResponse transfer(TransferRequest transferRequest) throws TransferException, AccountDoesNotExistsException;

   List<Account>findAllAccounts();

   ShowBalanceResponse showBalance(ShowBalanceRequest balanceRequest) throws AccountDoesNotExistsException;

   CloseAccountResponse close(CloseAccountRequest request) throws AccountDoesNotExistsException;

   AddAccountResponse addAccount(AddAccountRequest addAccountRequest) throws AccountAlreadyExistException;

 ViewAccountResponse  viewAccount(ViewAccountRequest viewAccountRequest) throws AccountDoesNotExistsException;

//    ViewAccountResponse viewAccount(ViewAccountRequest viewAccountRequest) throws AccountDoesNotExistsException;
//
//   Account findAccountByAccountNumber(String accountNumber);

}
