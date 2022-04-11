package com.bank.customer.command.api.controllers;

import com.bank.customer.command.api.commands.DeleteCustomerCommand;
import com.bank.customer.core.dto.Response;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/delete")
public class DeleteUserController {

    private final CommandGateway commandGateway;

    @Autowired
    public DeleteUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response> removeUser(@PathVariable(value = "id") String id) {
        try {
            commandGateway.send(new DeleteCustomerCommand(id));

            return new ResponseEntity<>(new Response("Customer successfully deleted!"), HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing remove user request for id - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new Response(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
