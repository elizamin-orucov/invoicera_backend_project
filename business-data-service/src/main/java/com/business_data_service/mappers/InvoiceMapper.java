package com.business_data_service.mappers;

import com.business_data_service.dtos.invoice.*;
import com.business_data_service.models.CategoryEntity;
import com.business_data_service.models.InvoiceEntity;
import com.business_data_service.models.enums.MeasurementUnit;
import com.business_data_service.repositories.CategoryRepository;
import com.business_data_service.repositories.InvoiceRepository;
import com.business_data_service.util.IdObfuscator;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {
    private final InvoiceRepository invoiceRepository;
    private final IdObfuscator idObfuscator;
    private final CategoryRepository categoryRepository;

    public InvoiceMapper(InvoiceRepository invoiceRepository, IdObfuscator idObfuscator, CategoryRepository categoryRepository) {
        this.invoiceRepository = invoiceRepository;
        this.idObfuscator = idObfuscator;
        this.categoryRepository = categoryRepository;
    }

    // to entity
    public InvoiceEntity toEntity(InvoiceCreateDto createDto) {
        Long categoryId = idObfuscator.decode(createDto.getCategory_id());
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        MeasurementUnit unit = MeasurementUnit.fromLabel(createDto.getUnitLabel());

        InvoiceEntity entity = new InvoiceEntity();
        entity.setCategory(category);
        entity.setUnit(unit);
        entity.setUnitPrice(createDto.getUnit_price());
        entity.setQuantity(createDto.getQuantity());
        entity.setTotalPrice(createDto.getTotal_price());
        entity.setExciseDegree(createDto.getExcise_degree());
        entity.setExcisePrice(createDto.getExcise_price());
        entity.setRoadTax(createDto.getRoad_tax());
        entity.setIdentificationNumber(createDto.getIdentification_number());
        entity.setVAT18Percent(createDto.getVAT18_percent());
        entity.setVAT0Percent(createDto.getVAT0_percent());
        entity.setVATExempt(createDto.getVAT_exempt());

        return entity;
    }

    public InvoiceEntity toEntity(InvoiceUpdateDto updateDto) {
        Long categoryId = idObfuscator.decode(updateDto.getCategory_id());
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        MeasurementUnit unit = MeasurementUnit.fromLabel(updateDto.getUnitLabel());

        InvoiceEntity entity = new InvoiceEntity();
        entity.setId(idObfuscator.decode(updateDto.getInvoice_id()));
        entity.setCategory(category);
        entity.setUnit(unit);
        entity.setUnitPrice(updateDto.getUnit_price());
        entity.setQuantity(updateDto.getQuantity());
        entity.setTotalPrice(updateDto.getTotal_price());
        entity.setExciseDegree(updateDto.getExcise_degree());
        entity.setExcisePrice(updateDto.getExcise_price());
        entity.setRoadTax(updateDto.getRoad_tax());
        entity.setIdentificationNumber(updateDto.getIdentification_number());
        entity.setVAT18Percent(updateDto.getVAT18_percent());
        entity.setVAT0Percent(updateDto.getVAT0_percent());
        entity.setVATExempt(updateDto.getVAT_exempt());

        return entity;
    }

    // to dto
    public InvoiceListDto toListDto(InvoiceEntity entity) {
        InvoiceListDto dto = new InvoiceListDto();
        dto.setId(idObfuscator.encode(entity.getId()));
        dto.setName(entity.getCategory().getCategoryName());
        dto.setCode(entity.getCategory().getCategoryCode());
        dto.setIdentification_number(entity.getIdentificationNumber());
        dto.setUnit(entity.getUnit().name());
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setTotalPrice(entity.getTotalPrice());
        return dto;
    }

    public InvoiceDetailDto toDetailDto(InvoiceEntity entity){
        InvoiceDetailDto dto = new InvoiceDetailDto();

        dto.setId(idObfuscator.encode(entity.getId()));
        dto.setName(entity.getCategory().getCategoryName());
        dto.setUnit(entity.getUnit().getLabel());
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setExciseDegree(entity.getExciseDegree());
        dto.setExcisePrice(entity.getExcisePrice());
        dto.setRoadTax(entity.getRoadTax());
        dto.setIdentificationNumber(entity.getIdentificationNumber());
        dto.setVAT18Percent(entity.getVAT18Percent());
        dto.setVAT0Percent(entity.getVAT0Percent());
        dto.setVATExempt(entity.getVATExempt());

        return dto;
    }

    public InvoiceResponseDto toResponseDto(InvoiceEntity entity){
        InvoiceResponseDto dto = new InvoiceResponseDto();
        dto.setId(idObfuscator.encode(entity.getId()));
        dto.setName(entity.getCategory().getCategoryName());
        dto.setCode(entity.getCategory().getCategoryCode());
        return dto;
    }
}
