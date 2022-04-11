package com.bank.customer.core.events;

import com.bank.customer.core.models.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerUpdatedEvent {
    private String id;
    private Customer customer;
}

