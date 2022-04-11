package com.bank.customer.command.api.controllers;

import com.bank.customer.command.api.commands.UpdateCustomerCommand;
import com.bank.customer.core.dto.Response;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/update")
public class UpdateCustomerController {
    private final CommandGateway commandGateway;

    @Autowired
    public UpdateCustomerController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response> updateCustomer(@PathVariable(value = "id") String id,
                                                   @Valid @RequestBody UpdateCustomerCommand command) {
        try {
            command.setId(id);
            commandGateway.send(command);

            return new ResponseEntity<>(new Response("Customer successfully updated!"), HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing update user request for id - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new Response(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
