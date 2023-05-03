package com.coupons.controller;

import com.coupons.dto.CustomerDto;
import com.coupons.entities.Customer;
import com.coupons.exceptions.ServerException;
import com.coupons.logic.CustomersLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private CustomersLogic customersLogic;

    @Autowired
    public CustomersController(CustomersLogic customersLogic){
        this.customersLogic = customersLogic;
    }

    @PostMapping
    public void createCustomers(@RequestBody Customer customer) throws ServerException {
        customersLogic.addCustomer(customer);
    }

    @PutMapping
    public void updateCustomer(@RequestBody Customer customer) throws ServerException {
        customersLogic.updateCustomer(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") int customerId) throws ServerException {
        customersLogic.removeCustomer(customerId);
    }

    @GetMapping("{customerId}")
    public CustomerDto getCustomer(@PathVariable("customerId") int customerId) throws ServerException {
        CustomerDto customer = customersLogic.getCustomer(customerId);
        return customer;
    }

    @GetMapping
    public List<CustomerDto> getAllCustomer(@RequestParam("page") int page) throws ServerException {
        List<CustomerDto> customers = customersLogic.getAllCustomers(page);
        return customers;
    }
}
