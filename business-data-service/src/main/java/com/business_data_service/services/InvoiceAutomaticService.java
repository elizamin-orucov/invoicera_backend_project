package com.business_data_service.services;

import com.business_data_service.dtos.invoice.InvoiceAutomaticRequest;
import com.business_data_service.dtos.invoice.InvoiceAutomaticResponse;
import com.business_data_service.dtos.response.ApiResponseDto;

public interface InvoiceAutomaticService {
    ApiResponseDto<InvoiceAutomaticResponse> createInvoice(InvoiceAutomaticRequest request);
}
