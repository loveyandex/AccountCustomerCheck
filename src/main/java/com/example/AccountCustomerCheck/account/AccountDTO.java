package com.example.AccountCustomerCheck.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import java.util.stream.Collectors;

import com.example.AccountCustomerCheck.customer.Customer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private List<Long> customerIds; // Assuming customers are identified by their IDs
    // Add other properties as needed

    // Constructor to convert from Account to AccountDTO
    public AccountDTO(Account account) {
        this.id = account.getId();
        this.accountNumber = account.getAccountNumber();
        this.customerIds = account.getCustomers().stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
        // Map other properties accordingly
    }
    
    // Static method to create AccountDTO from Account
    public static AccountDTO fromAccount(Account account) {
        return new AccountDTO(account);
    }
}
