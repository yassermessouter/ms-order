package com.esisba.msorder.order.dtos;


import com.esisba.msorder.order.OrderState;
import com.esisba.msorder.productRow.ProductRowDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRowDto {
    private String date;
    private String orderId;
    private String customerName;
    private Double totalAmount;
    private OrderState orderState;
}
