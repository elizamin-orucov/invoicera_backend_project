package com.business_data_service.dtos.invoice;

import com.business_data_service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvoiceAutomaticResponse extends BaseIdDto {
    private String message;
    private boolean success;
    private String customerName;
    private String productName;
}
