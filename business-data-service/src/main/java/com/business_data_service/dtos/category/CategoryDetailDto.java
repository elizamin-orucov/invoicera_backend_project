package com.business_data_service.dtos.category;

import com.business_data_service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CategoryDetailDto extends BaseIdDto {
    private String category_name;
    private List<CategoryListDto> children;
}
