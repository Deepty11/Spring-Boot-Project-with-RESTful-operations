package com.example.project.services;

import com.example.project.bean.Customer;
import com.example.project.dao.CustomerDao;
import com.example.project.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//RESTful operations
@RestController
@RequestMapping(value="/customer")
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @PostMapping
    public void addRecord(@RequestBody Customer customer){

        customerDao.addCustomer(customer);
    }
    @GetMapping
    public List<Customer> showRecords(){
        return customerDao.showRecord();
    }
    @GetMapping(value="/{customerId}")
    public Customer showCustomerRecord(@PathVariable("customerId") int id) throws EmptyResultDataAccessException {
        return customerDao.showCustomerRecords(id);
    }
    @PutMapping(value="/{customerId}")
    public void updateRecord(@PathVariable("customerId") int id, @RequestBody Customer customer){
        customerDao.updateRecord(id,customer);
    }
    @DeleteMapping(value="{customerId}")
    public void deleteRecord(@PathVariable("customerId") int id){
        customerDao.deleteRecord(id);
    }
}
