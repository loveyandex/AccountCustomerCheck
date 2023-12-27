package com.example.AccountCustomerCheck.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository; // Assuming you have an AccountRepository

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        return optionalAccount.orElse(null);
    }

    public Account createAccount(AccountDTO accountDTO) {
        // Create a new Account entity based on the DTO
        Account newAccount = new Account();
        // Populate fields based on the DTO
        // For example: newAccount.setAccountNumber(accountDTO.getAccountNumber());
        // Note: Handle relationships such as customers, issuedChecks, and receivedChecks appropriately
        return accountRepository.save(newAccount);
    }

    public Account updateAccount(Long accountId, AccountDTO accountDTO) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            Account existingAccount = optionalAccount.get();
            // Update fields based on the DTO
            // For example: existingAccount.setAccountNumber(accountDTO.getAccountNumber());
            // Note: Handle relationships such as customers, issuedChecks, and receivedChecks appropriately
            return accountRepository.save(existingAccount);
        } else {
            return null; // Account not found
        }
    }

    public boolean deleteAccount(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            accountRepository.delete(optionalAccount.get());
            return true;
        } else {
            return false; // Account not found
        }
    }
}
