package com.business_data_service.dtos.invoice;

import com.business_data_service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class InvoiceAutomaticListDto extends BaseIdDto {
    private String customerName;
    private String productName;
    private BigDecimal price;
    private LocalDate date_of_payment;
}
