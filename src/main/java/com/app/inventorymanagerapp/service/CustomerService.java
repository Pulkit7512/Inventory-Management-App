package com.app.inventorymanagerapp.service;

import com.app.inventorymanagerapp.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAll();

    public Customer findById(int id);

    public void save(Customer customer);

    public void deleteById(int id);

    public boolean findByEmailId(Customer c);

}
