package com.business_data_service.dtos.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductUpdateDto extends ProductCreateDto{
    private String productID;
}
