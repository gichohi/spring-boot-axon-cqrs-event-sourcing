package com.bank.customer.core.events;

import lombok.Data;

@Data
public class CustomerDeletedEvent {
    private String id;
}
