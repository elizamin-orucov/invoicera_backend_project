package com.business_data_service.dtos.customer;

import com.business_data_service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDetailDto extends BaseIdDto {
    private String customerName;

    private String customerSurname;

    private String contact;

    private String position;

    private String institution;

    private String TIN;
}
