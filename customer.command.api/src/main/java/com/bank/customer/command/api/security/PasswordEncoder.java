package com.bank.customer.command.api.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
