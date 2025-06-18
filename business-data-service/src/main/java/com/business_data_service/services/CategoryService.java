package com.business_data_service.services;

import com.business_data_service.dtos.category.CategoryCreateDto;
import com.business_data_service.dtos.category.CategoryDetailDto;
import com.business_data_service.dtos.category.CategoryListDto;
import com.business_data_service.dtos.category.CategoryUpdateDto;
import com.business_data_service.dtos.category.CategoryResponseDto;

import java.util.List;

public interface CategoryService extends BaseCRUDService<
        CategoryDetailDto, CategoryListDto, CategoryCreateDto, CategoryUpdateDto, CategoryResponseDto
        >{
    List<CategoryListDto> getChildrenByParentId(String parentId);
}
