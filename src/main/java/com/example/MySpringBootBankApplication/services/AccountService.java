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
    ChangePinResponse changePin(ChangePinRequest changePinRequest) throws AccountNotFoundException, javax.security.auth.login.AccountNotFoundException;

    DepositAccountResponse deposit(DepositAccountRequest depositAccountRequest) throws DepositException, javax.security.auth.login.AccountNotFoundException;
    WithdrawalAccountResponse withdrawal(WithdrawalAccountRequest withdrawalAccountRequest)
            throws javax.security.auth.login.AccountNotFoundException, WithdrawalException;

    TransferResponse transfer(TransferRequest transferRequest) throws TransferException, javax.security.auth.login.AccountNotFoundException;

   List<Account>findAllAccounts();

   ShowBalanceResponse showBalance(ShowBalanceRequest balanceRequest) throws javax.security.auth.login.AccountNotFoundException;

   CloseAccountResponse close(CloseAccountRequest request) throws javax.security.auth.login.AccountNotFoundException;

   AddAccountResponse addAccount(AddAccountRequest addAccountRequest) throws AccountAlreadyExistException;
//
//   Account findAccountByAccountNumber(String accountNumber);

}
