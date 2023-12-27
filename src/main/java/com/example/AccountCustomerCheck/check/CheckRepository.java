package com.example.AccountCustomerCheck.check;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CheckRepository extends JpaRepository<Check, Long> {
    // Your custom query methods, if any

    List<Check> findByIssuerAccount_Customers_IdOrReceiverAccount_Customers_Id(Long customerId, Long customerId2);

    @Query("SELECT c FROM cheuqe c " +
            "WHERE :customerId MEMBER OF c.issuerAccount.customers " +
            "   OR :customerId MEMBER OF c.receiverAccount.customers")
    List<Check> findIssuerAccountChecks(@Param("customerId") Long customerId);

    @Query("SELECT c FROM cheuqe c " +
            "WHERE :customerId IN (SELECT cust.id FROM c.issuerAccount.customers cust) " +
            "   OR :customerId IN (SELECT cust.id FROM c.receiverAccount.customers cust)")
    List<Check> findByIssuerAccount_Customers_IdOrReceiverAccount_Customers_Id2(@Param("customerId") Long customerId);

}
