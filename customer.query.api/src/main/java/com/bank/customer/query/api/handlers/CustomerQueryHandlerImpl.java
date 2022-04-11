package com.bank.customer.query.api.handlers;

import com.bank.customer.core.models.Customer;
import com.bank.customer.query.api.dto.CustomerLookupResponse;
import com.bank.customer.query.api.queries.FindAllCustomersQuery;
import com.bank.customer.query.api.queries.FindCustomerByIdQuery;
import com.bank.customer.query.api.queries.SearchCustomersQuery;
import com.bank.customer.query.api.repositories.CustomerRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerQueryHandlerImpl implements CustomerQueryHandler{
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerQueryHandlerImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @QueryHandler
    @Override
    public CustomerLookupResponse getCustomerById(FindCustomerByIdQuery query) {
        Optional<Customer> customer = customerRepository.findById(query.getId());
        return customer.map(CustomerLookupResponse::new).orElse(null);
    }

    @QueryHandler
    @Override
    public CustomerLookupResponse searchCustomers(SearchCustomersQuery query) {
        List<Customer> customerList = new ArrayList<>(customerRepository.findByFilterRegex(query.getFilter()));
        return new CustomerLookupResponse(customerList);
    }

    @QueryHandler
    @Override
    public CustomerLookupResponse getAllCustomers(FindAllCustomersQuery query) {
        List<Customer> customerList = new ArrayList<>(customerRepository.findAll());
        return new CustomerLookupResponse(customerList);
    }
}
