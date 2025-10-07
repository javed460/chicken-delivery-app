package com.chickendelivery.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    @NotEmpty(message = "Delivery address is required")
    private String deliveryAddress;
}