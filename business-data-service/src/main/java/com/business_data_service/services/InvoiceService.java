package com.business_data_service.services;

import com.business_data_service.dtos.invoice.*;

public interface InvoiceService extends BaseCRUDService<
        InvoiceDetailDto, InvoiceListDto, InvoiceCreateDto, InvoiceUpdateDto, InvoiceResponseDto
        >{
}
