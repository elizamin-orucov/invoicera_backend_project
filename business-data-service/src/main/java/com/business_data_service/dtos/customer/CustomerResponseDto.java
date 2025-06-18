package com.business_data_service.dtos.customer;

import com.business_data_service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerResponseDto extends BaseIdDto {
    private String customer_name;

    private String customer_surname;
}
