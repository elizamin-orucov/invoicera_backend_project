package com.business_data_service.dtos.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryCreateDto {
    private String parent;

    @NotBlank(message = "Category name cannot be empty.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String name;
}
