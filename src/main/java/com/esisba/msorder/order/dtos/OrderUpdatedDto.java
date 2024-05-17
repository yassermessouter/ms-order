package com.esisba.msorder.order.dtos;

import com.esisba.msorder.order.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdatedDto {
    private String date;
    private OrderState orderState;
    private Double payedAmount;
}
