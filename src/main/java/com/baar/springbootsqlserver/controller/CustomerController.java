package com.baar.springbootsqlserver.controller;

import com.baar.springbootsqlserver.model.Customer;
import com.baar.springbootsqlserver.service.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerServiceImpl customerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public String save(@RequestBody Customer customer) {
        logger.info("Saving customer");
        customerService.save(customer);
        return "Customer saved";
    }

    @GetMapping("/get/all")
    public Iterable<Customer> findAll() {
        logger.info("Returning all customers");
        return customerService.findAll();
    }
}
