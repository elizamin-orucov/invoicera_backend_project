package com.business_data_service.mappers;

import com.business_data_service.dtos.category.CategoryCreateDto;
import com.business_data_service.dtos.category.CategoryDetailDto;
import com.business_data_service.dtos.category.CategoryListDto;
import com.business_data_service.dtos.category.CategoryUpdateDto;
import com.business_data_service.dtos.category.CategoryResponseDto;
import com.business_data_service.models.CategoryEntity;
import com.business_data_service.repositories.CategoryRepository;
import com.business_data_service.util.IdObfuscator;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private final CategoryRepository categoryRepository;
    private final IdObfuscator idObfuscator;

    public CategoryMapper(CategoryRepository categoryRepository, IdObfuscator idObfuscator) {
        this.categoryRepository = categoryRepository;
        this.idObfuscator = idObfuscator;
    }

    public CategoryEntity toEntity(CategoryCreateDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setCategoryName(dto.getName());

        if (dto.getParent() != null) {
            CategoryEntity parent = categoryRepository.findById(idObfuscator.decode(dto.getParent()))
                    .orElseThrow(() -> new RuntimeException("Parent not found"));
            entity.setParent(parent);
        }

        return entity;
    }

    public CategoryEntity toEntity(CategoryUpdateDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(idObfuscator.decode(dto.getCategory_id()));
        entity.setCategoryName(dto.getName());

        if (dto.getParent() != null) {
            CategoryEntity parent = categoryRepository.findById(idObfuscator.decode(dto.getParent()))
                    .orElseThrow(() -> new RuntimeException("Parent not found"));
            entity.setParent(parent);
        }

        return entity;
    }

    public CategoryResponseDto toResponseDto(CategoryEntity entity){
        CategoryResponseDto response = new CategoryResponseDto();

        response.setId(idObfuscator.encode(entity.getId()));
        response.setCategoryName(entity.getCategoryName());
        response.setCategory_code(entity.getCategoryCode());

        return response;
    }

    public CategoryListDto toListDto(CategoryEntity entity) {

        CategoryListDto dto = new CategoryListDto();
        dto.setId(idObfuscator.encode(entity.getId()));
        dto.setCategory_name(entity.getCategoryName());
        dto.setCategory_code(entity.getCategoryCode());

        return dto;
    }

    public CategoryDetailDto toDetailDto(CategoryEntity entity){

        CategoryDetailDto detailDto = new CategoryDetailDto();
        detailDto.setId(idObfuscator.encode(entity.getId()));
        detailDto.setCategory_name(entity.getCategoryName());
        detailDto.setChildren(entity.getChildren().stream().map(this::toListDto).toList());

        return detailDto;
    }
}
