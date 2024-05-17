package com.esisba.msorder.order.dtos;

import com.esisba.msorder.order.OrderState;
import com.esisba.msorder.productRow.ProductRowDescriptionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDescriptionDto {
    private String date; //
    private String orderId; //
    private String customerName; //
    private String customerEmail; //
    private String customerAddress; //
    private OrderState orderState; //
    private List<ProductRowDescriptionDto> productRows;
    private Double totalAmount;
    private Double payedAmount;

}
