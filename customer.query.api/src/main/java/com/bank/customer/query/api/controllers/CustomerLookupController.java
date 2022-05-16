package com.bank.customer.query.api.controllers;

import com.bank.customer.query.api.dto.CustomerLookupResponse;
import com.bank.customer.query.api.queries.FindAllCustomersQuery;
import com.bank.customer.query.api.queries.FindCustomerByIdQuery;
import com.bank.customer.query.api.queries.SearchCustomersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerLookupController {
    private final QueryGateway queryGateway;

    @Autowired
    public CustomerLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/")
    public ResponseEntity<CustomerLookupResponse> getAllCustomers() {
        try {
            FindAllCustomersQuery query = new FindAllCustomersQuery();
            CustomerLookupResponse response = queryGateway.query(query, ResponseTypes.instanceOf(CustomerLookupResponse.class)).join();

            if (response == null || response.getCustomers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete get all customers request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new CustomerLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<CustomerLookupResponse> getCustomerById(@PathVariable(value = "id") String id) {
        try {
            FindCustomerByIdQuery query = new FindCustomerByIdQuery(id);
            CustomerLookupResponse response = queryGateway.query(query, ResponseTypes.instanceOf(CustomerLookupResponse.class)).join();

            if (response == null || response.getCustomers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete get customer by ID request";

            return new ResponseEntity<>(new CustomerLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/filter/{filter}")
    public ResponseEntity<CustomerLookupResponse> searchCustomerByFilter(@PathVariable(value = "filter") String filter) {
        try {
            SearchCustomersQuery query = new SearchCustomersQuery(filter);
            CustomerLookupResponse response = queryGateway.query(query, ResponseTypes.instanceOf(CustomerLookupResponse.class)).join();

            if (response == null || response.getCustomers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete customer search request";

            return new ResponseEntity<>(new CustomerLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
