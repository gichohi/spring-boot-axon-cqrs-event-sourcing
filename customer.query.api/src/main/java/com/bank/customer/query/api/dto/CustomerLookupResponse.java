package com.bank.customer.query.api.dto;

import com.bank.customer.core.dto.Response;
import com.bank.customer.core.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerLookupResponse extends Response {

    private List<Customer> customers;
    public CustomerLookupResponse(String message) {
        super(message);
    }

    public CustomerLookupResponse(List<Customer> customers) {
        super(null);
        this.customers = customers;
    }

    public CustomerLookupResponse(String message, Customer customer) {
        super(message);
        this.customers = new ArrayList<>();
        this.customers.add(customer);
    }

    public CustomerLookupResponse(Customer customer) {
        super(null);
        this.customers = new ArrayList<>();
        this.customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
