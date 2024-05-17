package com.esisba.msorder.productRow;

import com.esisba.msorder.order.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductRow {

    @Id
    @GeneratedValue()
    private Integer id;
    private String IdProduct;
    private Double product_price;
    private Integer qte;
    @ManyToOne
    @JoinColumn(
            name = "order_id"
    )
    @JsonBackReference
    private Order order;


}
