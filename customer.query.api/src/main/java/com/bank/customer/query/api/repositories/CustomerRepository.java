package com.bank.customer.query.api.repositories;

import com.bank.customer.core.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    @Query("{'$or' : [{'firstName': {$regex : ?0, $options: '1'}}, {'lastName': {$regex : ?0, $options: '1'}}, {'email': {$regex : ?0, $options: '1'}}, {'account.userName': {$regex : ?0, $options: '1'}}]}")
    List<Customer> findByFilterRegex(String filter);
}
