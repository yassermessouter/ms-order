package com.esisba.msorder.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDescriptionDto {
    private String name;
    private String email;
    private String address;
    private List<OrderRowDto> orderRowDtos;
}
