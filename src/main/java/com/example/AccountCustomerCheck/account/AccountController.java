package com.example.AccountCustomerCheck.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AccountCustomerCheck.customer.CustomerDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService; // Assuming you have an AccountService


    private AccountWithCustomersDTO convertToAccountWithCustomersDTO(Account account) {
        AccountWithCustomersDTO dto = new AccountWithCustomersDTO();
        dto.setAccountId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setCustomers(
                account.getCustomers().stream()
                        .map(CustomerDTO::fromCustomer) // Assuming you have a static method in CustomerDTO
                        .collect(Collectors.toList())
        );

     
        return dto;
    }

    @GetMapping
    public ResponseEntity<List<AccountWithCustomersDTO>> getAllAccounts() {
        List<AccountWithCustomersDTO> accountDTOList = accountService.getAllAccounts()
                .stream()
                .map(this::convertToAccountWithCustomersDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            AccountDTO accountDTO = new AccountDTO(account);
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        Account savedAccount = accountService.createAccount(accountDTO);
        AccountDTO savedAccountDTO = new AccountDTO(savedAccount);
        return new ResponseEntity<>(savedAccountDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountDTO> updateAccount(
            @PathVariable Long accountId,
            @RequestBody AccountDTO accountDTO) {
        Account updatedAccount = accountService.updateAccount(accountId, accountDTO);
        if (updatedAccount != null) {
            AccountDTO updatedAccountDTO = new AccountDTO(updatedAccount);
            return new ResponseEntity<>(updatedAccountDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        if (accountService.deleteAccount(accountId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
