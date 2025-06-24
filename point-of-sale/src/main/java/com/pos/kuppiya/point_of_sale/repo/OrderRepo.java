package com.pos.kuppiya.point_of_sale.repo;

import com.pos.kuppiya.point_of_sale.dto.QueryInterfaces.OrderDetailsInterface;
import com.pos.kuppiya.point_of_sale.entity.Customer;
import com.pos.kuppiya.point_of_sale.entity.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query(value = "select C.customer_name as customerName,C.customer_address as customerAddress,C.contact_numbers as contactNumbers,O.order_date as orderDate, O.total as total   from customer C,orders O where O.active_state=?1 and C.customer_id=O.customer_id",nativeQuery = true)
    List<OrderDetailsInterface> getPaidOrderDetails(boolean status, Pageable pageable);

    @Query(value = "select count(*) from customer C,orders O where O.active_state=?1 and C.customer_id=O.customer_id",nativeQuery = true)
    long getOrderDetails(boolean status);
}
