package com.business_data_service.services;

import com.business_data_service.dtos.product.*;

public interface ProductService extends BaseCRUDService<
        ProductDetailDto, ProductListDto, ProductCreateDto, ProductUpdateDto, ProductResponseDto
        >{
}
