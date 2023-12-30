package com.example.AccountCustomerCheck.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AccountCustomerCheck.account.Account;
import com.example.AccountCustomerCheck.account.AccountService;

import java.util.List;

import java.util.Optional;

@Service
public class CheckService {

    @Autowired
    private CheckRepository checkRepository; // Assuming you have a CheckRepository


    public List<Check> getChecksForCustomer(Long customerId) {
        // Assuming you have a method in CheckRepository for the custom query
        // E.g., findByIssuerAccount_Customers_IdOrReceiverAccount_Customers_Id
        return checkRepository.findByIssuerAccount_Customers_IdOrReceiverAccount_Customers_Id(customerId, customerId);
    }


    public List<Check> getChecksForCustomerQuery1(Long customerId) {
        // Assuming you have a method in CheckRepository for the custom query
        // E.g., findByIssuerAccount_Customers_IdOrReceiverAccount_Customers_Id
        return checkRepository.findIssuerAccountChecks(customerId);
    }

    public List<Check> getChecksForCustomerQuery2(Long customerId) {
        // Assuming you have a method in CheckRepository for the custom query
        // E.g., findByIssuerAccount_Customers_IdOrReceiverAccount_Customers_Id
        return checkRepository.findByIssuerAccount_Customers_IdOrReceiverAccount_Customers_Id2(customerId);
    }



    public List<Check> getAllChecks() {
        return checkRepository.findAll();
    }

    public Check getCheckById(Long checkId) {
        Optional<Check> optionalCheck = checkRepository.findById(checkId);
        return optionalCheck.orElse(null);
    }

    @Autowired
    private AccountService accountService; // Assuming you have an AccountService

    public Check createCheck(CheckDTO checkDTO) {
        // Retrieve issuer and receiver accounts
        Account issuerAccount = accountService.getAccountById(checkDTO.getIssuerAccountId());
        Account receiverAccount = accountService.getAccountById(checkDTO.getReceiverAccountId());

        if (issuerAccount != null && receiverAccount != null) {
            // Create a new Check entity
            Check newCheck = new Check(checkDTO, issuerAccount, receiverAccount);

            // Save the Check entity
            return checkRepository.save(newCheck);
        } else {
            // Handle case where either issuer or receiver account is not found
            return null;
        }
    }

    public Check updateCheck(Long checkId, CheckDTO checkDTO) {
        Optional<Check> optionalCheck = checkRepository.findById(checkId);
        if (optionalCheck.isPresent()) {
            Check existingCheck = optionalCheck.get();
            // Update fields based on the DTO
            // For example: existingCheck.setDescription(checkDTO.getDescription());
            return checkRepository.save(existingCheck);
        } else {
            return null; // Check not found
        }
    }

    public boolean deleteCheck(Long checkId) {
        Optional<Check> optionalCheck = checkRepository.findById(checkId);
        if (optionalCheck.isPresent()) {
            checkRepository.delete(optionalCheck.get());
            return true;
        } else {
            return false; // Check not found
        }
    }
}
