package com.example.AccountCustomerCheck.account;

import java.util.List;

import com.example.AccountCustomerCheck.customer.CustomerDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountWithCustomersDTO {
    private Long accountId;
    private String accountNumber;
    private List<CustomerDTO> customers;

    // Constructors, getters, and setters
}
