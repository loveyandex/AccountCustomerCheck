package com.example.AccountCustomerCheck.check;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
public class CheckDTO {
    private Long id;
    private String description;
    private Long issuerAccountId;
    private Long receiverAccountId;
    private Instant createdAt;

    // Constructors, getters, and setters

    public CheckDTO() {
        // Default constructor
    }

    public CheckDTO(Check check) {
        this.id = check.getId();
        this.description = check.getDescription();
        // Assuming issuerAccountId and receiverAccountId are properties of Check entity
        this.issuerAccountId = check.getIssuerAccount().getId();
        this.receiverAccountId = check.getReceiverAccount().getId();
        this.createdAt = check.getCreatedAt();
    }

    // Getters and setters
    // ...
}
