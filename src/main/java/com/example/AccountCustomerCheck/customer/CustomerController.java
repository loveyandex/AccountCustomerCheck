package com.example.AccountCustomerCheck.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService; // Assuming you have a CustomerService

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customerDTOList = customerService.getAllCustomers()
                .stream()
                .map(CustomerDTO::new) // Assuming a constructor in CustomerDTO that takes a Customer object
                .collect(Collectors.toList());
        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            CustomerDTO customerDTO = new CustomerDTO(customer);
            return new ResponseEntity<>(customerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer savedCustomer = customerService.createCustomer(customerDTO);
        CustomerDTO savedCustomerDTO = new CustomerDTO(savedCustomer);
        return new ResponseEntity<>(savedCustomerDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerDTO customerDTO) {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customerDTO);
        if (updatedCustomer != null) {
            CustomerDTO updatedCustomerDTO = new CustomerDTO(updatedCustomer);
            return new ResponseEntity<>(updatedCustomerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        if (customerService.deleteCustomer(customerId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
