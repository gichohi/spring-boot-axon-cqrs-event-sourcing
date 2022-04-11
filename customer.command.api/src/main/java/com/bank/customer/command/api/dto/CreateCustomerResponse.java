package com.bank.customer.command.api.dto;

public class CreateUserResponse extends Response {
    private String id;

    public CreateUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}