package com.pos.point_of._sale.repository;

import com.pos.point_of._sale.dto.CustomerDTO;
import com.pos.point_of._sale.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@EnableJpaRepositories
@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    List<Customer> findAllByCustomerNameEquals (String customerName);

    List<Customer> findAllByActiveStateEquals(boolean b);

@Modifying
@Query(value = "update customer set customer_name=?1 ,nic=?2 where customer_id=?3", nativeQuery=true )
    void updateCustomerQueryById(String customerName, String nic, int id);
}
