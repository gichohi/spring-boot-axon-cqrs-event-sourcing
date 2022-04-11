package com.bank.customer.command.api.dto;

import com.bank.customer.core.dto.Response;

public class CreateCustomerResponse extends Response {
    private String id;

    public CreateCustomerResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}