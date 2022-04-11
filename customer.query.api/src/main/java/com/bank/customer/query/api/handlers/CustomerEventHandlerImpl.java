package com.bank.customer.query.api.handlers;

import com.bank.customer.core.events.CustomerCreatedEvent;
import com.bank.customer.core.events.CustomerDeletedEvent;
import com.bank.customer.core.events.CustomerUpdatedEvent;
import com.bank.customer.query.api.repositories.CustomerRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("customer-group")
public class CustomerEventHandlerImpl implements CustomerEventHandler{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerEventHandlerImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @EventHandler
    @Override
    public void on(CustomerCreatedEvent event) {
        this.customerRepository.save(event.getCustomer());
    }

    @EventHandler
    @Override
    public void on(CustomerUpdatedEvent event) {
        this.customerRepository.save(event.getCustomer());
    }

    @EventHandler
    @Override
    public void on(CustomerDeletedEvent event) {
        this.customerRepository.deleteById(event.getId());
    }
}
