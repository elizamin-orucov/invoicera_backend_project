package com.business_data_service.dtos.invoice;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvoiceUpdateDto extends InvoiceCreateDto{
    @NotBlank(message = "Invoice id cannot be empty.")
    private String id;
}
