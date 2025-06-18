package com.business_data_service.dtos.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerUpdateDto extends CustomerCreateDto {
    @NotBlank(message = "Customer id cannot be empty.")
    private String customer_id;
}
