package com.pos.point_of._sale.repository;

import com.pos.point_of._sale.dto.CustomerDTO;
import com.pos.point_of._sale.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    List<Customer> findAllByCustomerNameEquals (String customerName);
}
