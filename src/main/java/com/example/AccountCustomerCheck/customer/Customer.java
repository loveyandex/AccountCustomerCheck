package com.example.AccountCustomerCheck.customer;
 
import java.util.ArrayList;
import java.util.List;

import com.example.AccountCustomerCheck.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstName;
    private String lastName; 
    

     
    @JsonIgnore
    @ManyToMany(mappedBy = "customers")
    private List<Account> accounts = new ArrayList<>();

    // Other customer-related attributes, getters, and setters

    public Customer() {
        // Default constructor
    }

    // Other constructors, getters, and setters

    public void addAccount(Account account) {
        accounts.add(account);
        account.getCustomers().add(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.getCustomers().remove(this);
    }
    // Constructor for creating Customer from DTO
    public Customer(CustomerDTO customerDTO) {
        this.firstName = customerDTO.getFirstName();
        this.lastName = customerDTO.getLastName();
        // Map other properties accordingly
    }
    // Other helper methods
}
