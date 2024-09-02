package com.baar.springbootsqlserver.service;

import com.baar.springbootsqlserver.model.Customer;
import com.baar.springbootsqlserver.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        customerRepository.findById(customer.getId()).ifPresent(c -> {
            throw new RuntimeException("Customer already exists");
        });
        logger.info("Saving customer");

        customer.setEmail(customer.getLastName().concat(customer.getFirstName()).concat("@someplace.com").toLowerCase());
        Customer savedCustomer = customerRepository.save(customer);

        logger.info("Customer saved: {}", savedCustomer);
        return savedCustomer;
    }

    @Override
    public Iterable<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll();
        // Sort customers by LastName.
        customers.sort((c2, c1) -> c1.getLastName().compareTo(c2.getLastName()));
        logger.info("Returning all customers");
        return customers;
    }
}
