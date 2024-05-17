package com.esisba.msorder.order.dtos;

import com.esisba.msorder.order.OrderState;
import com.esisba.msorder.productRow.ProductRow;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Integer id;
    private String supplierName;
    private String date;
    private Double totalAmount;
    private OrderState orderState;
}
