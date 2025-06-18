package com.business_data_service.dtos.category;

import com.business_data_service.base.BaseIdDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryResponseDto extends BaseIdDto {
    private String category_code;
    private String categoryName;
}
