package com.business_data_service.mappers;

import com.business_data_service.dtos.invoice.*;
import com.business_data_service.models.CustomerEntity;
import com.business_data_service.models.InvoiceEntity;
import com.business_data_service.models.InvoiceFastEntity;
import com.business_data_service.models.ProductEntity;
import com.business_data_service.models.enums.MeasurementUnit;
import com.business_data_service.repositories.CustomerRepository;
import com.business_data_service.repositories.InvoiceRepository;
import com.business_data_service.repositories.ProductRepository;
import com.business_data_service.util.IdObfuscator;
import com.business_data_service.util.VoenEncryptor;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {
    private final InvoiceRepository invoiceRepository;
    private final IdObfuscator idObfuscator;
    private final VoenEncryptor encryptor;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public InvoiceMapper(InvoiceRepository invoiceRepository, IdObfuscator idObfuscator, VoenEncryptor encryptor, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.invoiceRepository = invoiceRepository;
        this.idObfuscator = idObfuscator;
        this.encryptor = encryptor;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    // to entity
    public InvoiceEntity toEntity(InvoiceCreateDto createDto) {

        MeasurementUnit unit = MeasurementUnit.fromLabel(createDto.getUnitLabel());

        InvoiceEntity entity = new InvoiceEntity();

        entity.setRecipientName(createDto.getRecipientName());
        entity.setTIN(encryptor.encrypt(createDto.getTIN()));
        entity.setType(createDto.getType());
        entity.setSeries(createDto.getSeries());
        entity.setNumber(createDto.getNumber());
        entity.setName(createDto.getName());
        entity.setCode(createDto.getCode());
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

    public InvoiceFastEntity toEntity(FastInvoiceCreateDto createDto){
        InvoiceFastEntity entity = new InvoiceFastEntity();

        CustomerEntity customerEntity = customerRepository.findById(idObfuscator.decode(createDto.getCustomerID()))
                .orElseThrow(() -> new EntityNotFoundException("Müştəri tapılmadı: " + createDto.getCustomerID()));

        ProductEntity productEntity = productRepository.findById(idObfuscator.decode(createDto.getProductID()))
                .orElseThrow(() -> new EntityNotFoundException("Məhsul tapılmadı: " + createDto.getProductID()));


        entity.setCustomerName(customerEntity.getCustomerName());
        entity.setTotalAmount(createDto.getTotalAmount());
        entity.setQuantity(createDto.getQuantity());
        entity.setProductName(productEntity.getProductName());

        return entity;
    }

    public InvoiceEntity toEntity(InvoiceUpdateDto updateDto) {
        MeasurementUnit unit = MeasurementUnit.fromLabel(updateDto.getUnitLabel());

        InvoiceEntity entity = new InvoiceEntity();
        entity.setId(idObfuscator.decode(updateDto.getInvoice_id()));
        entity.setRecipientName(updateDto.getRecipientName());
        entity.setTIN(encryptor.encrypt(updateDto.getTIN()));
        entity.setType(updateDto.getType());
        entity.setSeries(updateDto.getSeries());
        entity.setNumber(updateDto.getNumber());
        entity.setName(updateDto.getName());
        entity.setCode(updateDto.getCode());
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

        dto.setTIN(entity.getTIN());
        dto.setNumber(entity.getNumber());
        dto.setSeries(entity.getSeries());
        dto.setType(entity.getType());
        dto.setId(idObfuscator.encode(entity.getId()));
        dto.setRecipientName(entity.getRecipientName());
        dto.setTIN(encryptor.decrypt(entity.getTIN()));
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setIdentification_number(entity.getIdentificationNumber());
        dto.setUnit(entity.getUnit().name());
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public InvoiceDetailDto toDetailDto(InvoiceEntity entity){
        InvoiceDetailDto dto = new InvoiceDetailDto();

        dto.setId(idObfuscator.encode(entity.getId()));

        dto.setRecipientName(entity.getRecipientName());
        dto.setTIN(encryptor.decrypt(entity.getTIN()));
        dto.setCode(entity.getCode());
        dto.setType(entity.getType());
        dto.setSeries(entity.getSeries());
        dto.setNumber(entity.getNumber());
        dto.setName(entity.getName());
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
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        return dto;
    }

    public FastInvoiceResponseDto toResponseDto(InvoiceFastEntity entity){
        FastInvoiceResponseDto dto = new FastInvoiceResponseDto();
        dto.setCustomerName(idObfuscator.encode(entity.getId()));
        dto.setProductName(entity.getProductName());
        dto.setCustomerName(entity.getCustomerName());
        dto.setTotalAmount(entity.getTotalAmount());
        return dto;
    }
}
