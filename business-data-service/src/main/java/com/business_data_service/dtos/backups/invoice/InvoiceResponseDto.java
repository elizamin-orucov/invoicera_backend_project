package com.business_data_service.dtos.backups.invoice;

import com.business_data_service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvoiceResponseDto extends BaseIdDto {
    private String name;
    private String code;
}


