package com.example.AccountCustomerCheck.check;

import java.time.Instant;

import com.example.AccountCustomerCheck.account.Account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "cheuqe")
@Getter
@Setter
@NoArgsConstructor
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "issuer_account_id")
    private Account issuerAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id")
    private Account receiverAccount;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    // Constructors, getters, and setters

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    // Constructor for creating Check from DTO
    public Check(CheckDTO checkDTO, Account issuerAccount, Account receiverAccount) {
        this.description = checkDTO.getDescription();
        this.createdAt = Instant.now();
        this.issuerAccount = issuerAccount;
        this.receiverAccount = receiverAccount;
        // Map other properties accordingly

    }

}
