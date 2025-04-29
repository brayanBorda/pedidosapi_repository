package com.virtualcoffee.pedidosapi.model;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
public class Order {
    private String id;

    @NotBlank
    private String name;

    @Pattern(regexp = "small|medium|large")
    private String size;

    private String status = "pending";
}
