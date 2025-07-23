package com.business_data_service.dtos.invoice;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class FastInvoiceResponseDto {
    private String customerName;
    private int quantity;
    private String productName;
    private BigDecimal totalAmount;
}
