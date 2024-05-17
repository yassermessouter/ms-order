package com.esisba.msorder.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findBySupplierName(String name);
    List<Order> findByCustomerName(String name);
    List<Order> findByCustomerNameAndSupplierName(String customerName,String supplierName);
}
