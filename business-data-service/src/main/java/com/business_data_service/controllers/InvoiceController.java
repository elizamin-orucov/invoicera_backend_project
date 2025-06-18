package com.business_data_service.controllers;

import com.business_data_service.controllers.base.BaseController;
import com.business_data_service.dtos.invoice.*;
import com.business_data_service.dtos.response.ApiResponseDto;
import com.business_data_service.services.InvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoiceController implements BaseController<
        InvoiceDetailDto,
        InvoiceListDto,
        InvoiceCreateDto,
        InvoiceUpdateDto,
        InvoiceResponseDto
        > {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<InvoiceListDto>> fetchAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page-1, size);
        return ResponseEntity.ok(invoiceService.list(pageable));
    }

    @Override
    @GetMapping("/{invoice_id}")
    public ResponseEntity<InvoiceDetailDto> fetchById(@PathVariable("invoice_id") String id) {
        InvoiceDetailDto response = invoiceService.detail(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponseDto<InvoiceResponseDto>> create(@RequestBody InvoiceCreateDto createDto) {
        ApiResponseDto<InvoiceResponseDto> response = invoiceService.create(createDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponseDto<InvoiceResponseDto>> update(@RequestBody InvoiceUpdateDto updateDto) {
        ApiResponseDto<InvoiceResponseDto> response = invoiceService.create(updateDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponseDto<InvoiceResponseDto>> delete(@RequestParam String id) {
        ApiResponseDto<InvoiceResponseDto> response = invoiceService.delete(id);
        return ResponseEntity.ok(response);
    }
}
