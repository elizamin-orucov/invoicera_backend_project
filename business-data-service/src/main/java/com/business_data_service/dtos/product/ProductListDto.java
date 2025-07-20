package com.business_data_service.dtos.product;

import com.business_data_service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductListDto extends BaseIdDto {
    private String productName;
    private String productCode;
}
