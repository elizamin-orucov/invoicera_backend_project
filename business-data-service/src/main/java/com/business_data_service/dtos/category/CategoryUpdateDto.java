package com.business_data_service.dtos.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryUpdateDto extends CategoryCreateDto{
    @NotBlank(message = "Category id cannot be empty.")
    private String category_id;
}
