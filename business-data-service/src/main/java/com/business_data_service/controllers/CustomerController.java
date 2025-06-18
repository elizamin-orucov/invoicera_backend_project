package com.business_data_service.controllers;

import com.business_data_service.controllers.base.BaseController;
import com.business_data_service.dtos.customer.*;
import com.business_data_service.dtos.response.ApiResponseDto;
import com.business_data_service.services.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController implements BaseController<
        CustomerDetailDto,
        CustomerListDto,
        CustomerCreateDto,
        CustomerUpdateDto,
        CustomerResponseDto
        > {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<CustomerListDto>> fetchAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page-1, size);
        return ResponseEntity.ok(customerService.list(pageable));
    }

    @Override
    @GetMapping("/{customer_id}")
    public ResponseEntity<CustomerDetailDto> fetchById(@PathVariable("customer_id") String id) {
        CustomerDetailDto response = customerService.detail(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponseDto<CustomerResponseDto>> create(CustomerCreateDto createDto) {
        ApiResponseDto<CustomerResponseDto> response = customerService.create(createDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponseDto<CustomerResponseDto>> update(CustomerUpdateDto updateDto) {
        ApiResponseDto<CustomerResponseDto> response = customerService.update(updateDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponseDto<CustomerResponseDto>> delete(String id) {
        ApiResponseDto<CustomerResponseDto> response = customerService.delete(id);
        return ResponseEntity.ok(response);
    }
}
