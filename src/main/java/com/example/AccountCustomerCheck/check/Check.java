package com.example.AccountCustomerCheck.check;
 
import java.time.Instant;

import com.example.AccountCustomerCheck.account.Account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "cheuqe")
@Getter
@Setter
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
}
