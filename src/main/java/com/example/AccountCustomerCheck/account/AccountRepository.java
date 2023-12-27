package com.example.AccountCustomerCheck.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // You can add custom query methods if needed
}
