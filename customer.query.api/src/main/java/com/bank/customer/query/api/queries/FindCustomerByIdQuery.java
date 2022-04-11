package com.bank.customer.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCustomerByIdQuery {
    private String id;
}