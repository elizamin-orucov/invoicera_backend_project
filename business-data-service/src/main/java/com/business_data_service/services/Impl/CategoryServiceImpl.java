package com.business_data_service.services.Impl;

import com.business_data_service.dtos.category.CategoryCreateDto;
import com.business_data_service.dtos.category.CategoryDetailDto;
import com.business_data_service.dtos.category.CategoryListDto;
import com.business_data_service.dtos.category.CategoryUpdateDto;
import com.business_data_service.dtos.response.ApiResponseDto;
import com.business_data_service.dtos.category.CategoryResponseDto;
import com.business_data_service.mappers.CategoryMapper;
import com.business_data_service.models.CategoryEntity;
import com.business_data_service.repositories.CategoryRepository;
import com.business_data_service.services.CategoryService;
import com.business_data_service.util.IdObfuscator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.business_data_service.util.RandomCodeGenerator.generateRandomCode;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;
    private final IdObfuscator idObfuscator;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper mapper, IdObfuscator idObfuscator) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.idObfuscator = idObfuscator;
    }

    @Override
    public Page<CategoryListDto> list(Pageable pageable) {
        Page<CategoryEntity> entities = categoryRepository.findAll(pageable);
        return entities.map(mapper::toListDto);
    }

    @Override
    public List<CategoryListDto> list() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        return entities.stream().map(mapper::toListDto).toList();
    }

    @Override
    public CategoryDetailDto detail(String id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(idObfuscator.decode(id));
        return optional.map(mapper::toDetailDto).orElse(null);
    }

    @Override
    public ApiResponseDto<CategoryResponseDto> create(CategoryCreateDto categoryCreateDto) {
        CategoryEntity entity = mapper.toEntity(categoryCreateDto);

        String newUniqueCode = generateUniqueCategoryCode(11);
        entity.setCategoryCode(newUniqueCode);

        if (categoryCreateDto.getParent() != null && !categoryCreateDto.getParent().isBlank()) {
            Long parentId = idObfuscator.decode(categoryCreateDto.getParent());
            CategoryEntity parentEntity = categoryRepository.findById(parentId).orElse(null);
            entity.setParent(parentEntity);
        }

        CategoryEntity newEntity = categoryRepository.save(entity);
        return ApiResponseDto.<CategoryResponseDto>builder()
                .message("created new category")
                .response(mapper.toResponseDto(newEntity))
                .success(true)
                .build();
    }

    @Override
    public ApiResponseDto<CategoryResponseDto> update(CategoryUpdateDto categoryUpdateDto) {
        CategoryEntity entity = mapper.toEntity(categoryUpdateDto);
        CategoryEntity updateEntity = categoryRepository.save(entity);
        return ApiResponseDto.<CategoryResponseDto>builder()
                .message("updated category")
                .response(mapper.toResponseDto(updateEntity))
                .success(true)
                .build();
    }

    @Override
    public ApiResponseDto<CategoryResponseDto> delete(String id) {
        CategoryEntity entity = categoryRepository.findById(idObfuscator.decode(id)).orElseThrow();
        categoryRepository.delete(entity);
        return ApiResponseDto.<CategoryResponseDto>builder()
                .message("successfully deleted")
                .success(true)
                .response(mapper.toResponseDto(entity))
                .build();
    }

    @Override
    public List<CategoryListDto> getChildrenByParentId(String parentId) {
        return categoryRepository.findByParentId(idObfuscator.decode(parentId)).stream().map(mapper::toListDto).toList();
    }

    public String generateUniqueCategoryCode(int length) {
        String code;
        do {
            code = generateRandomCode(length);
        } while (categoryRepository.existsByCategoryCode(code));
        return code;
    }
}
