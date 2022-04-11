package com.bank.customer.query.api.handlers;

import com.bank.customer.query.api.dto.CustomerLookupResponse;
import com.bank.customer.query.api.queries.FindAllCustomersQuery;
import com.bank.customer.query.api.queries.FindCustomerByIdQuery;
import com.bank.customer.query.api.queries.SearchCustomersQuery;

public interface CustomerQueryHandler {
    CustomerLookupResponse getCustomerById(FindCustomerByIdQuery query);
    CustomerLookupResponse searchCustomers(SearchCustomersQuery query);
    CustomerLookupResponse getAllCustomers(FindAllCustomersQuery query);
}
