package com.pos.kuppiya.point_of_sale.dto.QueryInterfaces;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailsInterface {
    String getCustomerName();
    String getCustomerAddress();
    ArrayList getContactNumbers();
    Date getOrderDate();
    double getTotal();
}
