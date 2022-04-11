package com.bank.customer.query.api.handlers;

import com.bank.customer.core.events.CustomerCreatedEvent;
import com.bank.customer.core.events.CustomerDeletedEvent;
import com.bank.customer.core.events.CustomerUpdatedEvent;

public interface CustomerEventHandler {
    void on(CustomerCreatedEvent event);
    void on(CustomerUpdatedEvent event);
    void on(CustomerDeletedEvent event);
}
