package com.example.MySpringBootBankApplication.services;

import com.example.MySpringBootBankApplication.data.model.Account;
import com.example.MySpringBootBankApplication.data.repository.AccountRepository;
import com.example.MySpringBootBankApplication.data.repository.BankUserRepository;
import com.example.MySpringBootBankApplication.dtos.AccountDtos.request.*;
import com.example.MySpringBootBankApplication.dtos.AccountDtos.responses.*;
//import com.example.MySpringBootBankApplication.exception.AccountException.ChangePinException;
import com.example.MySpringBootBankApplication.exception.AccountException.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired

    private BankUserRepository bankUserRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest){
//
        if (bankUserRepository.findBankUserByEmailAddress(createAccountRequest.getEmailAddress()).isPresent()) throw new CreateAccountException("email exist");
        Account account = new Account();
            account.setAccountFirstName(createAccountRequest.getAccountFirstName());
            account.setAccountLastName(createAccountRequest.getAccountLastName());
            account.setAccountType(createAccountRequest.getAccountType());
            account.setEmailAddress(createAccountRequest.getEmailAddress());
            account.setPin(createAccountRequest.getPin());
            account.generateAccountNumber();

            accountRepository.save(account);
            CreateAccountResponse createAccountResponse = new CreateAccountResponse();

            createAccountResponse.setAccountNumber(account.getAccountNumber());
            createAccountResponse.setMessage("Account created Successfully");

            return createAccountResponse;
    }
    @Override
    public ChangePinResponse changePin(ChangePinRequest changePinRequest) throws AccountDoesNotExistsException {
        ChangePinResponse changePinResponse = new ChangePinResponse();
        Optional<Account> accountFound = accountRepository.findByAccountNumber(changePinRequest.getAccountNumber());

        if (accountFound.isEmpty()) {
            throw new AccountDoesNotExistsException("Account not found");
        }
            Account account = accountFound.get();
            account.setPin(changePinRequest.getPin());
            accountRepository.save(account);

            changePinResponse.setAccountNumber(account.getAccountNumber());
            changePinResponse.setPin(account.getPin());
            changePinResponse.setMessage("Pin changed successfully");

             return changePinResponse;

        }



    @Override
    public DepositAccountResponse deposit(DepositAccountRequest depositAccountRequest) throws AccountDoesNotExistsException ,DepositException {
        DepositAccountResponse depositAccountResponse = new DepositAccountResponse();
        Optional<Account> accountFound = accountRepository.findByAccountNumber(depositAccountRequest.getAccountNumber());
        if(accountFound.isEmpty())throw new AccountDoesNotExistsException("Account not found");
        Account account = accountFound.get();
//        if(depositAccountRequest.getDepositAmount() < 0) throw new DepositException("Enter a valid deposit amount");
         validateDepositAmount(depositAccountRequest);
        account.setBalance(depositAccountRequest.getDepositAmount().add(account.getBalancee()));
        accountRepository.save(account);
        depositAccountResponse.setBalance(account.getBalance());
        depositAccountResponse.setMessage("Deposit successful");
        return depositAccountResponse;
    }

    private void validateDepositAmount(DepositAccountRequest depositRequest) throws DepositException {
        BigDecimal depositAmount = new BigDecimal(0);
        if (depositRequest.getDepositAmount().compareTo(depositAmount) < 0) {
            throw new DepositException("Enter a valid amount");
        }
    }

    @Override
    public WithdrawalAccountResponse withdrawal(WithdrawalAccountRequest withdrawalAccountRequest) throws
            AccountDoesNotExistsException, WithdrawalException {
        WithdrawalAccountResponse withdrawalAccountResponse = new WithdrawalAccountResponse();
        Optional<Account> accountFound = accountRepository.findByAccountNumber(withdrawalAccountRequest.getAccountNumber());
        if(accountFound.isEmpty())throw new AccountDoesNotExistsException("Account not found");
        Account account = accountFound.get();

        if(withdrawalAccountRequest.getWithdrawalAmount().compareTo(account.getBalance()) > 0)
            throw new WithdrawalException("Invalid amount");

        account.setBalance(account.getBalance().subtract(withdrawalAccountRequest.getWithdrawalAmount() ));
        accountRepository.save(account);

        withdrawalAccountResponse.setBalance(account.getBalance());
        withdrawalAccountResponse.setMessage("Withdrawal successful");

        return withdrawalAccountResponse;




    }

    @Override
    public TransferResponse transfer(TransferRequest transferRequest) throws AccountDoesNotExistsException, TransferException {
        TransferResponse response = new TransferResponse();
        Optional<Account> depositorAccountFound = accountRepository.findByAccountNumber(transferRequest.getDepositorAccountNumber());
        if(depositorAccountFound.isEmpty())throw new AccountDoesNotExistsException("Account not found");
        Account depositor = depositorAccountFound.get();

        Optional<Account> receiverAccountFound = accountRepository.findByAccountNumber(transferRequest.getRecipientAccountNumber());
        if(receiverAccountFound.isEmpty())throw new AccountDoesNotExistsException("Account does not exist");
        Account receiver = receiverAccountFound.get();

        if(depositor.getBalance().compareTo(transferRequest.getTransferAmount()) < 0)
            throw new TransferException("You do not have enough money to make transfer....");

        depositor.setBalance(depositor.getBalance().subtract(transferRequest.getTransferAmount()));
        receiver.setBalance(transferRequest.getTransferAmount().add(receiver.getBalance()));
        accountRepository.save(depositor);
        accountRepository.save(receiver);


        response.setMessage("Transfer sent successful");
        response.setDepositorBalance(depositor.getBalance());
        response.setRecipientBalance(receiver.getBalance());

        return response;


    }




    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();

    }

    @Override
    public ShowBalanceResponse showBalance(ShowBalanceRequest balanceRequest) throws AccountDoesNotExistsException{
       ShowBalanceResponse balanceResponse = new ShowBalanceResponse();
        Optional<Account> accountFound = accountRepository.findByAccountNumber(balanceRequest.getAccountNumber());
        if(accountFound.isEmpty())throw new AccountDoesNotExistsException("Account not found");
        Account account = accountFound.get();


        balanceResponse.setBalance(account.getBalance());
        balanceResponse.setAccountNumber(account.getAccountNumber());

        return balanceResponse;
    }

    @Override
    public CloseAccountResponse close(CloseAccountRequest request) throws AccountDoesNotExistsException {
      CloseAccountResponse closeAccountResponse = new CloseAccountResponse();

        Optional<Account> accountFound = accountRepository.findByAccountNumber(request.getAccountNumber());
        if(accountFound.isEmpty())throw new AccountDoesNotExistsException("Account not found");
        Account account = accountFound.get();

        accountRepository.delete(account);


        closeAccountResponse.setMessage("Account closed");
        return closeAccountResponse;

    }

    @Override
    public AddAccountResponse addAccount(AddAccountRequest addAccountRequest) throws AccountAlreadyExistException {
        AddAccountResponse addAccountResponse = new AddAccountResponse();
        Account account = new Account();
        if (!bankUserRepository.existsById(addAccountRequest.getEmailAddress())) {
            account.setAccountFirstName(addAccountRequest.getAccountFirstName());
            account.setAccountLastName(addAccountRequest.getAccountLastName());
            account.setAccountType(addAccountRequest.getAccountType());
            account.setEmailAddress(addAccountRequest.getEmailAddress());
            account.setPin(addAccountRequest.getPin());
            account.setBalance(account.getBalancee());
            account.generateAccountNumber();

            accountRepository.insert(account);
            addAccountResponse.setAccountNumber(account.getAccountNumber());

            addAccountResponse.setMessage("Account added");

            return addAccountResponse;

        }
        else{
          throw new AccountAlreadyExistException("Account already exist");
        }
    }

    @Override
    public ViewAccountResponse viewAccount(ViewAccountRequest viewAccountRequest) throws AccountDoesNotExistsException {

      Optional<Account> accountFound = accountRepository.findByEmailAddress(viewAccountRequest.getEmailAddress());

        if(accountFound.isEmpty()) throw new AccountDoesNotExistsException("Account does not exist");

        Account account = accountFound.get();
//
        ViewAccountResponse viewAccountResponse = new ViewAccountResponse();
//        List<ViewAccountResponse> viewAccountResponse2 = new ArrayList<>();
//        account.forEach(account2 -> {
        viewAccountResponse.setAccountNumber(account.getAccountNumber());
        viewAccountResponse.setAccountType(account.getAccountType());
        viewAccountResponse.setEmailAddress(account.getEmailAddress());
        viewAccountResponse.setFirstName(account.getAccountFirstName());
        viewAccountResponse.setLastName(account.getAccountLastName());
        viewAccountResponse.setPin(account.getPin());


        return viewAccountResponse;
    }

//    @Override
//    public ViewAccountResponse viewAccount(ViewAccountRequest viewAccountRequest) throws AccountDoesNotExistsException {
////        ViewAccountResponse viewAccountResponse = new ViewAccountResponse();
//        Optional <Account> accountFound = accountRepository.findByEmailAddress(viewAccountRequest.getEmailAddress());
//
//        if(accountFound.isEmpty()) throw new AccountDoesNotExistsException("Account does not exist");
//
//        Account account = accountFound.get();
//
//        viewAccountResponse.setAccountNumber(account.getAccountNumber());
//        viewAccountResponse.setAccountType(account.getAccountType());
//        viewAccountResponse.setEmailAddress(account.getEmailAddress());
//        viewAccountResponse.setFirstName(account.getAccountFirstName());
//        viewAccountResponse.setLastName(account.getAccountLastName());
//
//        return viewAccountResponse;


//    }


}


