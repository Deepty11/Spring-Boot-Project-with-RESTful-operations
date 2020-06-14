package com.example.project.dao;

import com.example.project.bean.Customer;
import com.example.project.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Component
public class CustomerDao {
    int customerId=1;
    //List<Customer> customerlist= new CopyOnWriteArrayList<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addCustomer(Customer c){
        c.setId(customerId);
        customerId++;
        //customerlist.add(c);
        String sql="insert into customerDetails (name,age,email) values(?,?,?);";
        jdbcTemplate.update(sql,c.getName(),c.getAge(),c.getEmail());
        System.out.println("Customer added! ");


    }
    public List<Customer> showRecord(){
         String sql= "select * from customerDetails;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Customer>(Customer.class));
    }
    public Customer showCustomerRecords(int id){
        String sql="select * from customerDetails where id= ?;";
        try{
            return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<Customer>(Customer.class));
        }catch(Exception e){
            throw new CustomerNotFoundException ("Not Found");
        }

    }
    public void updateRecord(int id, Customer customer){
        String sql= "update customerDetails set name=?, age=?,email=? where id=?";
        jdbcTemplate.update(sql,customer.getName(),customer.getAge(),customer.getEmail(),id);
        System.out.println("Update Successful!");

        /*customerlist.stream()
               .forEach(c->{
                   if(c.getId()==id){
                       c.setName(customer.getName());
                       c.setEmail(customer.getEmail());
                       c.setAge(customer.getAge());
                   }
               });
        return customerlist.stream()
                .filter(c->c.getId()==id)
                .findFirst()
                .get();*/

    }
    public void deleteRecord(int id){
        String sql= "delete from customerDetails where id=?;";
        jdbcTemplate.update(sql,id);
        System.out.println("Deletion Successful!");
        /*customerlist.stream()
                .forEach(c->{
                    if(c.getId()==id){
                        customerlist.remove(c.getAge());
                        customerlist.remove(c.getName());
                        customerlist.remove(c.getEmail());
                    }
                });*/
    }
}
