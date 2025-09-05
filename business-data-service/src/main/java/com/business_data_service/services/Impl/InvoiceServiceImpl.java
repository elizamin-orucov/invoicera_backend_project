package com.business_data_service.services.Impl;

import com.business_data_service.dtos.invoice.*;
import com.business_data_service.dtos.response.ApiResponseDto;
import com.business_data_service.mappers.InvoiceMapper;
import com.business_data_service.models.CategoryEntity;
import com.business_data_service.models.InvoiceEntity;
import com.business_data_service.models.InvoiceFastEntity;
import com.business_data_service.models.ProductEntity;
import com.business_data_service.repositories.*;
import com.business_data_service.services.InvoiceService;
import com.business_data_service.util.IdObfuscator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper mapper;
    private final IdObfuscator idObfuscator;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final InvoiceFastRepository invoiceFastRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper mapper, IdObfuscator idObfuscator, ProductRepository productRepository, CustomerRepository customerRepository, InvoiceFastRepository invoiceFastRepository) {
        this.invoiceRepository = invoiceRepository;
        this.mapper = mapper;
        this.idObfuscator = idObfuscator;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.invoiceFastRepository = invoiceFastRepository;
    }

    @Override
    public Page<InvoiceListDto> list(Pageable pageable) {
        Page<InvoiceEntity> entities = invoiceRepository.findAll(pageable);
        return entities.map(mapper::toListDto);
    }

    @Override
    public InvoiceDetailDto detail(String id) {
        InvoiceEntity entity = invoiceRepository.findById(idObfuscator.decode(id)).orElse(null);

        if (entity == null){
            throw new EntityNotFoundException(id + " not found");
        }

        return mapper.toDetailDto(entity);
    }

    @Override
    public ApiResponseDto<InvoiceResponseDto> create(InvoiceCreateDto invoiceCreateDto) {
        ProductEntity productEntity = productRepository.findById(idObfuscator.decode(invoiceCreateDto.getProductID())).orElse(null);

        if (productEntity == null){
            throw new EntityNotFoundException("category not found");
        }

        InvoiceEntity entity = mapper.toEntity(invoiceCreateDto);
        entity.setProduct(productEntity);
        InvoiceEntity newEntity = invoiceRepository.save(entity);
        InvoiceResponseDto response = mapper.toResponseDto(newEntity);

        return ApiResponseDto.<InvoiceResponseDto>builder()
                .response(response)
                .success(true)
                .message("created new invoice")
                .build();
    }

    @Override
    public ApiResponseDto<InvoiceResponseDto> createTemplate(String id) {
        Long decodedId = idObfuscator.decode(id);
        InvoiceEntity original = invoiceRepository.findById(decodedId)
                .orElseThrow(() -> new EntityNotFoundException("Invoice with ID " + id + " not found"));

        InvoiceEntity template = new InvoiceEntity();

        template.setRecipientName(original.getRecipientName());
        template.setTIN(original.getTIN());
        template.setType(original.getType());
        template.setSeries(original.getSeries());
        template.setNumber(original.getNumber());
        template.setCode(original.getCode());
        template.setGroupName(original.getGroupName());
        template.setUnit(original.getUnit());
        template.setUnitPrice(original.getUnitPrice());
        template.setQuantity(original.getQuantity());
        template.setTotalPrice(original.getTotalPrice());
        template.setExciseDegree(original.getExciseDegree());
        template.setExcisePrice(original.getExcisePrice());
        template.setRoadTax(original.getRoadTax());
        template.setIdentificationNumber(original.getIdentificationNumber());
        template.setVAT18Percent(original.getVAT18Percent());
        template.setVAT0Percent(original.getVAT0Percent());
        template.setVATExempt(original.getVATExempt());

        InvoiceEntity savedTemplate = invoiceRepository.save(template);
        InvoiceResponseDto response = mapper.toResponseDto(savedTemplate);

        return ApiResponseDto.<InvoiceResponseDto>builder()
                .response(response)
                .success(true)
                .message("Created new invoice template")
                .build();
    }

    @Override
    public ApiResponseDto<FastInvoiceResponseDto> fastInvoiceCreate(FastInvoiceCreateDto createDto) {
        InvoiceFastEntity invoiceEntity = mapper.toEntity(createDto);
        InvoiceFastEntity savedEntity = invoiceFastRepository.save(invoiceEntity);
        FastInvoiceResponseDto response = mapper.toResponseDto(savedEntity);
        return ApiResponseDto.<FastInvoiceResponseDto>builder()
                .response(response)
                .success(true)
                .message("success")
                .build();
    }


    @Override
    public ApiResponseDto<InvoiceResponseDto> update(InvoiceUpdateDto invoiceUpdateDto) {
        InvoiceEntity entity = mapper.toEntity(invoiceUpdateDto);
        InvoiceEntity updatedEntity = invoiceRepository.save(entity);
        InvoiceResponseDto response = mapper.toResponseDto(updatedEntity);
        return ApiResponseDto.<InvoiceResponseDto>builder()
                .response(response)
                .success(true)
                .message("updated invoice")
                .build();
    }

    @Override
    public ApiResponseDto<InvoiceResponseDto> delete(String id) {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(idObfuscator.decode(id)).orElseThrow();
        invoiceRepository.delete(invoiceEntity);
        return ApiResponseDto.<InvoiceResponseDto>builder()
                .response(mapper.toResponseDto(invoiceEntity))
                .success(true)
                .message("successfully deleted")
                .build();
    }

}
