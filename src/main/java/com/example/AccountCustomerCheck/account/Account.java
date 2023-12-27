package com.example.AccountCustomerCheck.account;

  
import java.util.ArrayList;
import java.util.List;

import com.example.AccountCustomerCheck.check.Check;
import com.example.AccountCustomerCheck.customer.Customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    @ManyToMany
    @JoinTable(
        name = "customer_account",
        joinColumns = @JoinColumn(name = "account_id"),
        inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers = new ArrayList<>();

    @OneToMany(mappedBy = "issuerAccount", cascade = CascadeType.ALL)
    private List<Check> issuedChecks = new ArrayList<>();

    @OneToMany(mappedBy = "receiverAccount", cascade = CascadeType.ALL)
    private List<Check> receivedChecks = new ArrayList<>();

    // Other account-related attributes, getters, and setters

    public Account() {
        // Default constructor
    }

    // Other constructors, getters, and setters

    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.getAccounts().add(this);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
        customer.getAccounts().remove(this);
    }

    // Other helper methods
}
