package com.business_data_service.services.Impl;

import com.business_data_service.dtos.invoice.*;
import com.business_data_service.dtos.response.ApiResponseDto;
import com.business_data_service.mappers.InvoiceMapper;
import com.business_data_service.models.InvoiceEntity;
import com.business_data_service.repositories.InvoiceRepository;
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

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper mapper, IdObfuscator idObfuscator) {
        this.invoiceRepository = invoiceRepository;
        this.mapper = mapper;
        this.idObfuscator = idObfuscator;
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
        InvoiceEntity entity = mapper.toEntity(invoiceCreateDto);
        InvoiceEntity newEntity = invoiceRepository.save(entity);
        InvoiceResponseDto response = mapper.toResponseDto(newEntity);
        return ApiResponseDto.<InvoiceResponseDto>builder()
                .response(response)
                .success(true)
                .message("created new invoice")
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
