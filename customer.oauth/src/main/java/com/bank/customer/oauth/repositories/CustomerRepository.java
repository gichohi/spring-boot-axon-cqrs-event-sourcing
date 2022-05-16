package com.bank.customer.oauth.repositories;

import com.bank.customer.core.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    @Query("{'account.customerName':  ?0}")
    Optional<Customer> findByCustomerName(String customerName);
}
