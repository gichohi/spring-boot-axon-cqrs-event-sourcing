package com.bank.customer.command.api.commands;

import com.bank.customer.core.models.Customer;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateCustomerCommand {
    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "no customer details were supplied")
    @Valid
    private Customer customer;
}
