package com.pos.kuppiya.point_of_sale.service.impl;

import com.pos.kuppiya.point_of_sale.dto.QueryInterfaces.OrderDetailsInterface;
import com.pos.kuppiya.point_of_sale.dto.paginated.PaginatedResponseOrderDetailsDTO;
import com.pos.kuppiya.point_of_sale.dto.request.OrderSaveRequestDTO;
import com.pos.kuppiya.point_of_sale.dto.response.ResponseOrderDetailsDTO;
import com.pos.kuppiya.point_of_sale.entity.Order;
import com.pos.kuppiya.point_of_sale.entity.OrderDetails;
import com.pos.kuppiya.point_of_sale.repo.CustomerRepo;
import com.pos.kuppiya.point_of_sale.repo.ItemRepo;
import com.pos.kuppiya.point_of_sale.repo.OrderDetailRepo;
import com.pos.kuppiya.point_of_sale.repo.OrderRepo;
import com.pos.kuppiya.point_of_sale.service.OrderService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;


    @Override
    @Transactional
    public String addOrder(OrderSaveRequestDTO orderSaveRequestDTO) {
        Order order =new Order(
                customerRepo.getReferenceById(orderSaveRequestDTO.getCustomer()),
                orderSaveRequestDTO.getOrderDate(),
                orderSaveRequestDTO.getTotal()
        );
        orderRepo.save(order);

        if(orderRepo.existsById(order.getOrderId())){
            List<OrderDetails> orderDetails = modelMapper.map(orderSaveRequestDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>() {
            }.getType());
            for(int i=0;i<orderDetails.size();i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getReferenceById(orderSaveRequestDTO.getOrderDetails().get(i).getItems()));
            }

            if(orderDetails.size() > 0){
                orderDetailRepo.saveAll(orderDetails);
            }
            return "saved";
        }
        return null;
    }

    @Override
    public PaginatedResponseOrderDetailsDTO getAllOrderDetails(boolean status, int page, int size) {
        List<OrderDetailsInterface> orderDetailsInterfaces = orderRepo.getPaidOrderDetails(status, PageRequest.of(page,size));
        System.out.println(orderDetailsInterfaces.get(0).getCustomerName());

        List<ResponseOrderDetailsDTO> responseOrderDetailsDTOS = new ArrayList<>();
        for(OrderDetailsInterface O : orderDetailsInterfaces){
//            ResponseOrderDetailsDTO responseOrderDetailsDTO = new ResponseOrderDetailsDTO(
//                    O.getCustomerName(),O.getCustomerAddress(),O.getContactNumbers(),O.getOrderDate(),O.getTotal()
//            );
//            responseOrderDetailsDTOS.add(responseOrderDetailsDTO);
            responseOrderDetailsDTOS.add(
                    new ResponseOrderDetailsDTO(
                            O.getCustomerName(),O.getCustomerAddress(),O.getContactNumbers(),O.getOrderDate(),O.getTotal()
                    )
            );
        }
        PaginatedResponseOrderDetailsDTO paginatedResponseOrderDetailsDTO = new PaginatedResponseOrderDetailsDTO(
                responseOrderDetailsDTOS,
                orderRepo.getOrderDetails(status)
        );
        return paginatedResponseOrderDetailsDTO;
    }
}
