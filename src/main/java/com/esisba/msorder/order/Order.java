package com.esisba.msorder.order;
import com.esisba.msorder.productRow.ProductRow;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_order")

public class Order {

    @Id
    @GeneratedValue
    private Integer id;
    private String supplierName;
    private String customerName;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductRow> productRows;
    private String date;
    private Double totalAmount;
    private OrderState orderState;
    private Double payedAmount;

}
