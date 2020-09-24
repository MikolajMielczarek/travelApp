package com.mm.controllers;


import com.mm.ResourceNotFoundException;
import com.mm.entity.Customer;
import com.mm.repositories.CustomerRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final CustomerRepository customerRepository;

    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    @GetMapping("/{customerId}")
    public Customer findById(@PathVariable(name = "customerId") Long customerId) throws ResourceNotFoundException {
        return customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("customer not found"));
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
        @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        customer.setSubject(customerDetails.getSubject());
        customer.setPerson(customerDetails.getPerson());
        customer.setPlace(customerDetails.getPlace());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhone(customerDetails.getPhone());
        customer.setLastName(customerDetails.getLastName());
        customer.setFirstName(customerDetails.getFirstName());
        final Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId)
        throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}



