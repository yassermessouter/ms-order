package com.esisba.msorder.order.dtos;

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
public class OrderRequestDto {

    private String supplierName;
    private String customerName;
    private List<ProductRowDto> productRows;
    private String date;
    private Double totalAmount;

}
