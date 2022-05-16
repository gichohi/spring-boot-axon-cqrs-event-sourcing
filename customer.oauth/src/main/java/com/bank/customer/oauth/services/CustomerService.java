package com.bank.customer.oauth.services;


import com.bank.customer.core.models.Account;
import com.bank.customer.core.models.Customer;
import com.bank.customer.oauth.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "customerService")
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByCustomerName(customerName);

        if (customer.isEmpty()) {
            throw new UsernameNotFoundException("Incorrect Username / Password supplied!");
        }
        Account account = customer.get().getAccount();

        return org.springframework.security.core.userdetails.User
                .withUsername(customerName)
                .password(account.getPassword())
                .authorities(account.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
