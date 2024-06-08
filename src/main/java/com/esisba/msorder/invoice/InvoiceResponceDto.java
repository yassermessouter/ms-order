package com.esisba.msorder.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponceDto {
    private String company_name;
    private String date;
    private Integer orderNumber;
    private String status;
}
