package com.business_data_service.dtos.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductCreateDto {
    private String productName;
    private String productCode;
    private BigDecimal price;
    private String weightUnit;
    private String gtin;
}
