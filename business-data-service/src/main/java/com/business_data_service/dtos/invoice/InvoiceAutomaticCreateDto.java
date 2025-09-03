package com.business_data_service.dtos.invoice;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class InvoiceAutomaticCreateDto {
    @NotBlank(message = "Customer ID must not be blank")
    private String customerID;

    @NotBlank(message = "Product ID must not be blank")
    private String productID;

    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Date must not be null")
    @Future(message = "Date must be in the future")
    private LocalDate date_of_payment;
}

