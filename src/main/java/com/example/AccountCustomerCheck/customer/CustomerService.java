package com.example.AccountCustomerCheck.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository; // Assuming you have a CustomerRepository

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        return optionalCustomer.orElse(null);
    }

    public Customer createCustomer(CustomerDTO customerDTO) {
        // Create a new Customer entity based on the DTO
        Customer newCustomer = new Customer();
        // Populate fields based on the DTO
        // For example: newCustomer.setFirstName(customerDTO.getFirstName());
        // Note: Handle relationships and other properties accordingly
        return customerRepository.save(newCustomer);
    }

    public Customer updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            // Update fields based on the DTO
            // For example: existingCustomer.setFirstName(customerDTO.getFirstName());
            // Note: Handle relationships and other properties accordingly
            return customerRepository.save(existingCustomer);
        } else {
            return null; // Customer not found
        }
    }

    public boolean deleteCustomer(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            customerRepository.delete(optionalCustomer.get());
            return true;
        } else {
            return false; // Customer not found
        }
    }
}
