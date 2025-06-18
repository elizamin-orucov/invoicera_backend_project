package com.business_data_service.dtos.invoice;

import com.business_data_service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class InvoiceListDto extends BaseIdDto {
    private String name;
    private String code;
    private String identification_number;
    private String unit;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
