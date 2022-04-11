package com.bank.customer.command.api.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class DeleteCustomerCommand {
    @TargetAggregateIdentifier
    private String id;
}
