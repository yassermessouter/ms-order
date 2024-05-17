package com.esisba.msorder.productRow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRowDescriptionDto {

    private String product_name;
    private Double product_price;
    private Integer qte;
    private Double amount;
}
