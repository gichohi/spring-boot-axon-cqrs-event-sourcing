package com.bank.customer.command.api.aggregates;

import com.bank.customer.command.api.commands.CreateCustomerCommand;
import com.bank.customer.command.api.commands.UpdateCustomerCommand;
import com.bank.customer.command.api.security.PasswordEncoder;
import com.bank.customer.command.api.security.PasswordEncoderImpl;
import com.bank.customer.core.events.CustomerCreatedEvent;
import com.bank.customer.core.events.CustomerUpdatedEvent;
import com.bank.customer.core.models.Customer;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class CustomerAggregate {
    @AggregateIdentifier

    private final PasswordEncoder passwordEncoder;

    public CustomerAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        Customer newCustomer = command.getCustomer();
        newCustomer.setId(command.getId());
        String password = newCustomer.getAccount().getPassword();
        passwordEncoder = new PasswordEncoderImpl();
        String hashedPassword = passwordEncoder.hashPassword(password);
        newCustomer.getAccount().setPassword(hashedPassword);

        CustomerCreatedEvent event = CustomerCreatedEvent.builder()
                .id(command.getId())
                .customer(newCustomer)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateCustomerCommand command) {
        Customer updatedCustomer = command.getCustomer();
        updatedCustomer.setId(command.getId());
        String password = updatedCustomer.getAccount().getPassword();
        String hashedPassword = passwordEncoder.hashPassword(password);
        updatedCustomer.getAccount().setPassword(hashedPassword);

        CustomerUpdatedEvent event = CustomerUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .customer(updatedCustomer)
                .build();

        AggregateLifecycle.apply(event);
    }
}
