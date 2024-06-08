package com.esisba.msorder.order;

import com.esisba.msorder.order.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*") // Allow requests from http://localhost:3000

public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping("/orders")
    public String createOrder(
            @RequestBody OrderRequestDto orderRequestDto
    ) {
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping("client/orders-by/{customer-name}")
    public List<OrderResponseDto> getCustomerOrders(
            @PathVariable("customer-name") String customerName) {
        return orderService.getOrders(customerName);
    }

    @GetMapping("client/orders/{id}")
    public Optional<Order> getOrderById(@PathVariable("id") String id) {
        return orderRepository.findById(Integer.parseInt(id));
    }

    @DeleteMapping("client/orders/{id}")
    public String cancelOrder(@PathVariable("id") String id) {
        orderRepository.deleteById(Integer.parseInt(id));
        return "Order canceled";
    }

    @GetMapping("orders/{supplier-name}")
    public List<OrderRowDto> getSupplierOrders(
            @PathVariable("supplier-name") String supplierName) {
        return orderService.getSupplierOrders(supplierName);
    }

    @GetMapping("supplier/orders/{id}")
    public OrderDescriptionDto getOrderDescription(
            @PathVariable("id") String id) {
        return orderService.getOrderDescription(Integer.parseInt(id));
    }

    @PutMapping("supplier/orders/{id}")
    public Order updateOrder(
            @PathVariable("id") String id, @RequestBody OrderUpdatedDto orderUpdatedDto) {
        return orderService.updateOrder(Integer.parseInt(id), orderUpdatedDto);
    }

    @GetMapping("/customers/{supplier-name}")
    public List<CustomerDto> getCustomers(
            @PathVariable("supplier-name") String supplierName) {
        return orderService.getCustomers(supplierName);
    }

    @GetMapping("/orders/{supplier-name}/{customer-name}")
    public CustomerDescriptionDto getCustomerOrders(
            @PathVariable("customer-name") String customerName,
            @PathVariable("supplier-name") String supplierName
    ) {
        return orderService.getCustomerOrders(supplierName, customerName);
    }


}
