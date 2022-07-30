package com.app.inventorymanagerapp.service;


import com.app.inventorymanagerapp.dao.CustomerRepository;
import com.app.inventorymanagerapp.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository custRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository custRepository){
        this.custRepository=custRepository;
    }


    @Override
    public List<Customer> findAll() {
        return custRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        Optional<Customer> result = custRepository.findById(id);
        Customer customer = null;
        if(result.isPresent()) {
            customer = result.get();
        }
        else{
            throw new RuntimeException("Did not find Customer Id- "+id);
        }
        return customer;
    }

    @Override
    public void save(Customer customer) {
        custRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        custRepository.deleteById(id);
    }

    public boolean findByEmailId(Customer c){
        return custRepository.findByEmail(c.getEmail())==null?false:true;
    }
}
