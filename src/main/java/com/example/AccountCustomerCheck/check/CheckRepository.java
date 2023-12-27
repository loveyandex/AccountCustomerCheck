package com.example.AccountCustomerCheck.check;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepository extends JpaRepository<Check, Long> {
    // Your custom query methods, if any
}
