package com.pos.point_of._sale.dto.request;

import com.pos.point_of._sale.entity.Customer;
import com.pos.point_of._sale.entity.Item;
import com.pos.point_of._sale.entity.Order;
import com.pos.point_of._sale.entity.OrderDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {

    private int customer;

    private Date date;

    private Double total;

    private List<RequestOrderDetailsSave> orderDetails;
}
