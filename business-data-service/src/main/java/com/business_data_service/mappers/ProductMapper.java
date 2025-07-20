package com.business_data_service.mappers;

import com.business_data_service.dtos.product.*;
import com.business_data_service.models.ProductEntity;
import com.business_data_service.models.enums.MeasurementUnit;
import com.business_data_service.util.IdObfuscator;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final IdObfuscator idObfuscator;

    public ProductMapper(IdObfuscator idObfuscator) {
        this.idObfuscator = idObfuscator;
    }

    // to entity
    public ProductEntity toEntity(ProductCreateDto createDto){
        ProductEntity productEntity = new ProductEntity();
        MeasurementUnit unit = MeasurementUnit.fromLabel(createDto.getWeightUnit());

        productEntity.setProductName(createDto.getProductName());
        productEntity.setProductCode(createDto.getProductCode());
        productEntity.setGtin(createDto.getGtin());
        productEntity.setPrice(createDto.getPrice());
        productEntity.setWeightUnit(unit);

        return productEntity;
    }

    public ProductEntity toEntity(ProductUpdateDto updateDto){
        ProductEntity productEntity = new ProductEntity();

        MeasurementUnit unit = MeasurementUnit.fromLabel(updateDto.getWeightUnit());

        productEntity.setId(idObfuscator.decode(updateDto.getProductID()));
        productEntity.setProductName(updateDto.getProductName());
        productEntity.setProductCode(updateDto.getProductCode());
        productEntity.setGtin(updateDto.getGtin());
        productEntity.setPrice(updateDto.getPrice());
        productEntity.setWeightUnit(unit);

        return productEntity;
    }

    // to dto
    public ProductListDto toListDto(ProductEntity productEntity){
        ProductListDto productListDto = new ProductListDto();

        productListDto.setId(idObfuscator.encode(productEntity.getId()));
        productListDto.setProductName(productEntity.getProductName());
        productListDto.setProductCode(productEntity.getProductCode());

        return productListDto;
    }

    public ProductDetailDto toDetailDto(ProductEntity productEntity){
        ProductDetailDto productDetailDto = new ProductDetailDto();

        productDetailDto.setId(idObfuscator.encode(productEntity.getId()));
        productDetailDto.setProductName(productEntity.getProductName());
        productDetailDto.setProductCode(productEntity.getProductCode());
        productDetailDto.setPrice(productEntity.getPrice());
        productDetailDto.setGtin(productEntity.getGtin());
        productDetailDto.setWeightUnit(productEntity.getWeightUnit().getLabel());

        return productDetailDto;
    }

    public ProductResponseDto toResponseDto(ProductEntity productEntity){
        ProductResponseDto response = new ProductResponseDto();

        response.setProductName(productEntity.getProductName());
        response.setSuccess(true);
        response.setMessage("successfully creates the product");

        return response;
    }

}
