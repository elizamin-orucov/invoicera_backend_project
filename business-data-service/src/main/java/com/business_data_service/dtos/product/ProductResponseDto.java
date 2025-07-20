package com.business_data_service.dtos.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponseDto {
    private String productName;
    private boolean success;
    private String message;
}
