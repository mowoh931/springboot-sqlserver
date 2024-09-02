package com.baar.springbootsqlserver.service;

import com.baar.springbootsqlserver.model.Customer;

public interface CustomerService {
    // Save a customer
    Customer save(Customer customer);

    // Find all customers
    Iterable<Customer> findAll();
}
