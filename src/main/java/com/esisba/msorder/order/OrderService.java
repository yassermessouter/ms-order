package com.esisba.msorder.order;

import com.esisba.msorder.order.dtos.*;
import com.esisba.msorder.productRow.ProductRow;
import com.esisba.msorder.productRow.ProductRowDescriptionDto;
import com.esisba.msorder.productRow.ProductRowDto;
import com.esisba.msorder.productRow.ProductRowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRowRepository productRowRepository;

    public String createOrder(OrderRequestDto orderRequestDto) {
        Order order = Order.builder()
                .customerName(orderRequestDto.getCustomerName())
                .supplierName(orderRequestDto.getSupplierName())
                .date(orderRequestDto.getDate())
                .totalAmount(orderRequestDto.getTotalAmount())
                .orderState(OrderState.PENDING)
                .payedAmount(0.0)
                .build();
        Order savedOrder = orderRepository.save(order);
        for (ProductRowDto productRowDto : orderRequestDto.getProductRows()) {
            ProductRow productRow = ProductRow.builder()
                    .order(savedOrder)
                    .IdProduct(productRowDto.getIdProduct())
                    .qte(productRowDto.getQte())
                    .product_price(productRowDto.getProduct_price())
                    .build();
            productRowRepository.save(productRow);

        }
        return "Order created successfully";
    }

    public List<OrderRowDto> getSupplierOrders(String supplierName) {
        List<OrderRowDto> orderRowDtos = new ArrayList<>();
        List<Order> orders = orderRepository.findBySupplierName(supplierName);
        Collections.reverse(orders);
        for (Order order : orders) {
            orderRowDtos.add(
                    OrderRowDto.builder()
                            .orderId(order.getId().toString())
                            .customerName(order.getCustomerName())
                            .orderState(order.getOrderState())
                            .date(order.getDate())
                            .totalAmount(order.getTotalAmount())
                            .build()
            );
        }
        return orderRowDtos;
    }

    public OrderDescriptionDto getOrderDescription(int id) {
        //get product name and product group by productId
        //get customer email and addres by customerId
        Order order = orderRepository.findById(id).orElseThrow();
        List<ProductRowDescriptionDto> productRowDescriptionDtos = new ArrayList<>();
        for (ProductRow productRow : order.getProductRows()) {
            productRowDescriptionDtos.add(
                    ProductRowDescriptionDto.builder()
                            .product_name("product name")
                            .product_price(productRow.getProduct_price())
                            .qte(productRow.getQte())
                            .amount(productRow.getProduct_price() * productRow.getQte())
                            .build()
            );
        }
        return OrderDescriptionDto.builder()
                .orderId(order.getId().toString())
                .orderState(order.getOrderState())
                .date(order.getDate())
                .customerName(order.getCustomerName())
                .totalAmount(order.getTotalAmount())
                .payedAmount(order.getPayedAmount())
                .customerAddress("customer address")
                .customerEmail("customer email")
                .productRows(productRowDescriptionDtos)
                .build();

    }

    public Order updateOrder(int id, OrderUpdatedDto orderUpdatedDto) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setOrderState(orderUpdatedDto.getOrderState());
        order.setDate(orderUpdatedDto.getDate());
        order.setPayedAmount(order.getPayedAmount() + orderUpdatedDto.getPayedAmount());
        if (order.getTotalAmount() == order.getPayedAmount()) {
            order.setOrderState(OrderState.PAYED);
        }
        return orderRepository.save(order);

    }

    public List<CustomerDto> getCustomers(String supplierName) {
        List<Order> orders = orderRepository.findBySupplierName(supplierName);
        Set<String> customerNames = new HashSet<>();
        List<CustomerDto> customers = new ArrayList<>();
        for (Order order : orders) {
            String customerName = order.getCustomerName();
            if (!customerNames.contains(customerName)) {
                customers.add(CustomerDto.builder()
                        .name(customerName)
                        .build());
                customerNames.add(customerName);
            }
        }
        for (CustomerDto customerDto : customers) {
            List<Order> customerOrders = orderRepository.findByCustomerNameAndSupplierName(
                    customerDto.getName(), supplierName
            );
            Boolean payment = true;
            for (Order order : customerOrders) {
                if (order.getOrderState() != OrderState.PAYED) {
                    payment = false;
                    break;
                }
            }
            customerDto.setPayment(payment);
            customerDto.setEmail("email");
            customerDto.setState("state");
        }
        return customers;
    }

    public CustomerDescriptionDto getCustomerOrders(String supplierName, String customerName) {
        CustomerDescriptionDto customer= CustomerDescriptionDto.builder()
                .name(customerName)
                .email("email")
                .address("address")
                .build();
        List<OrderRowDto> orderRowDtos = new ArrayList<>();
        List<Order> orders = orderRepository.findByCustomerNameAndSupplierName(customerName, supplierName);
        for (Order order : orders) {
            orderRowDtos.add(OrderRowDto.builder()
                    .totalAmount(order.getTotalAmount())
                    .date(order.getDate())
                    .orderState(order.getOrderState())
                    .customerName(order.getCustomerName())
                    .orderId(order.getId().toString())
                    .build());
        }
        customer.setOrderRowDtos(orderRowDtos);
        return customer;
    }

    public List<OrderResponseDto> getOrders(String customerName) {
       List<Order> orders= orderRepository.findByCustomerName(customerName);
       List<OrderResponseDto> orderResponseDtos=new ArrayList<>();
       for (Order order:orders){
           orderResponseDtos.add(OrderResponseDto.builder()
                           .id(order.getId())
                           .date(order.getDate())
                           .orderState(order.getOrderState())
                           .supplierName(order.getSupplierName())
                           .totalAmount(order.getTotalAmount())
                   .build());
       }
       return orderResponseDtos;


    }
}