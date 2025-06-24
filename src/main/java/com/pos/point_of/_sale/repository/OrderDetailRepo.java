package com.pos.point_of._sale.repository;

import com.pos.point_of._sale.entity.Order;
import com.pos.point_of._sale.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderDetailRepo extends JpaRepository<OrderDetails,Integer> {
}
