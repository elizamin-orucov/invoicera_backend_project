package com.business_data_service.services;

import com.business_data_service.dtos.invoice.InvoiceAutomaticCreateDto;
import com.business_data_service.dtos.invoice.InvoiceAutomaticResponse;
import com.business_data_service.dtos.invoice.InvoiceAutomaticUpdateDto;
import com.business_data_service.dtos.response.ApiResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InvoiceAutomaticService {
    ApiResponseDto<InvoiceAutomaticResponse> createInvoice(InvoiceAutomaticCreateDto request);
    ApiResponseDto<InvoiceAutomaticResponse> updateInvoice(InvoiceAutomaticUpdateDto request);
}
