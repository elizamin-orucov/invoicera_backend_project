package com.business_data_service.controllers;

import com.business_data_service.controllers.base.BaseController;
import com.business_data_service.dtos.product.*;
import com.business_data_service.dtos.response.ApiResponseDto;
import com.business_data_service.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController implements BaseController<
        ProductDetailDto,
        ProductListDto,
        ProductCreateDto,
        ProductUpdateDto,
        ProductResponseDto
        > {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ProductListDto>> fetchAll(@RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page-1, size);

        Page<ProductListDto> listDtos = productService.list(pageable);
        return ResponseEntity.ok(listDtos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDto> fetchById(@PathVariable String id) {
        ProductDetailDto detailDto = productService.detail(id);
        return ResponseEntity.ok(detailDto);
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> create(@RequestBody ProductCreateDto createDto) {
        ApiResponseDto<ProductResponseDto> response = productService.create(createDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> update(@RequestBody ProductUpdateDto updateDto) {
        ApiResponseDto<ProductResponseDto> response = productService.update(updateDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> delete(@RequestParam String id) {
        ApiResponseDto<ProductResponseDto> response = productService.delete(id);
        return ResponseEntity.ok(response);
    }
}
