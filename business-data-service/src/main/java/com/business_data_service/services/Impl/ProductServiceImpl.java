package com.business_data_service.services.Impl;

import com.business_data_service.dtos.product.*;
import com.business_data_service.dtos.response.ApiResponseDto;
import com.business_data_service.mappers.ProductMapper;
import com.business_data_service.models.ProductEntity;
import com.business_data_service.repositories.ProductRepository;
import com.business_data_service.services.ProductService;
import com.business_data_service.util.IdObfuscator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final IdObfuscator idObfuscator;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper mapper, IdObfuscator idObfuscator) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.idObfuscator = idObfuscator;
    }

    @Override
    public Page<ProductListDto> list(Pageable pageable) {
        Page<ProductEntity> entities = productRepository.findAll(pageable);
        return entities.map(mapper::toListDto);
    }

    @Override
    public ProductDetailDto detail(String id) {
        ProductEntity entity = productRepository.findById(idObfuscator.decode(id)).orElse(null);

        if(entity != null){
            return mapper.toDetailDto(entity);
        }

        return null;
    }

    @Override
    public ApiResponseDto<ProductResponseDto> create(ProductCreateDto createDto) {
        ProductEntity productEntity = mapper.toEntity(createDto);

        ProductEntity savedEntity = productRepository.save(productEntity);


        return ApiResponseDto.<ProductResponseDto>builder()
                .success(true)
                .message("product created!")
                .response(mapper.toResponseDto(savedEntity))
                .build();
    }

    @Override
    public ApiResponseDto<ProductResponseDto> update(ProductUpdateDto updateDto) {
        ProductEntity entity = mapper.toEntity(updateDto);

        productRepository.save(entity);
        return ApiResponseDto.<ProductResponseDto>builder()
                .success(true)
                .message("product created!")
                .response(mapper.toResponseDto(entity))
                .build();
    }

    @Override
    public ApiResponseDto<ProductResponseDto> delete(String id) {
        ProductEntity entity = productRepository.findById(idObfuscator.decode(id)).orElse(null);

        if (entity == null){
            throw new EntityNotFoundException(id + " not found");
        }

        ProductResponseDto response = mapper.toResponseDto(entity);
        response.setMessage("product successfully deleted");

        productRepository.delete(entity);
        return ApiResponseDto.<ProductResponseDto>builder()
                .response(response)
                .success(true)
                .message("successfully deleted")
                .build();
    }
}
