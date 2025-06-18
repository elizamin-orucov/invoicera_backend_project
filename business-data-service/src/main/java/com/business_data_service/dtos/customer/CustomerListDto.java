package com.business_data_service.dtos.customer;

import com.business_data_service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerListDto extends BaseIdDto {
    private String full_name;

    private String contact;

    private String position;

    private String institution;

    private String TIN;
}
