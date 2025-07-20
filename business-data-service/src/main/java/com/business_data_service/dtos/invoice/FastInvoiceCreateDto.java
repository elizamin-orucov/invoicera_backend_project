package com.business_data_service.dtos.invoice;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class FastInvoiceCreateDto {
    private String customerID;
    private int quantity;
    private String productID;
    private BigDecimal totalPrice;
}
