package com.example.AccountCustomerCheck.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    // Add other properties as needed

    // Constructor to convert from Customer to CustomerDTO
    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        // Map other properties accordingly
    }
    
    // Static method to create CustomerDTO from Customer
    public static CustomerDTO fromCustomer(Customer customer) {
        return new CustomerDTO(customer);
    }
}
