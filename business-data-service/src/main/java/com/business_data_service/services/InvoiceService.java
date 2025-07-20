package com.business_data_service.services;

import com.business_data_service.dtos.invoice.*;
import com.business_data_service.dtos.response.ApiResponseDto;

public interface InvoiceService extends BaseCRUDService<
        InvoiceDetailDto, InvoiceListDto, InvoiceCreateDto, InvoiceUpdateDto, InvoiceResponseDto
        >{
    ApiResponseDto<InvoiceResponseDto> createTemplate(String id);
    ApiResponseDto<InvoiceResponseDto> fastInvoiceCreate(FastInvoiceCreateDto createDto);
}
