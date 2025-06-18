package com.business_data_service.controllers;

import com.business_data_service.controllers.base.BaseController;
import com.business_data_service.dtos.category.CategoryCreateDto;
import com.business_data_service.dtos.category.CategoryDetailDto;
import com.business_data_service.dtos.category.CategoryListDto;
import com.business_data_service.dtos.category.CategoryUpdateDto;
import com.business_data_service.dtos.response.ApiResponseDto;
import com.business_data_service.dtos.category.CategoryResponseDto;
import com.business_data_service.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController implements BaseController<
        CategoryDetailDto,
        CategoryListDto,
        CategoryCreateDto,
        CategoryUpdateDto,
        CategoryResponseDto
        > {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<CategoryListDto>> fetchAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return ResponseEntity.ok(service.list(pageable));
    }

    @Override
    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDetailDto> fetchById(@PathVariable("category_id") String id) {
        return ResponseEntity.ok(service.detail(id));
    }

    @GetMapping("/children/{parent_id}")
    public ResponseEntity<List<CategoryListDto>> getChildren(@PathVariable("parent_id") String id) {
        List<CategoryListDto> children = service.getChildrenByParentId(id);
        return ResponseEntity.ok(children);
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponseDto<CategoryResponseDto>> create(@RequestBody CategoryCreateDto createDto) {
        ApiResponseDto<CategoryResponseDto> response = service.create(createDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponseDto<CategoryResponseDto>> update(@RequestBody CategoryUpdateDto categoryUpdateDto) {
        ApiResponseDto<CategoryResponseDto> response = service.update(categoryUpdateDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponseDto<CategoryResponseDto>> delete(@RequestParam String id) {
        ApiResponseDto<CategoryResponseDto> response = service.delete(id);
        return ResponseEntity.ok(response);
    }
}
