package com.business_data_service.services.Impl;

import com.business_data_service.dtos.customer.*;
import com.business_data_service.dtos.response.ApiResponseDto;
import com.business_data_service.mappers.CustomerMapper;
import com.business_data_service.models.CustomerEntity;
import com.business_data_service.repositories.CustomerRepository;
import com.business_data_service.services.CustomerService;
import com.business_data_service.util.IdObfuscator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final IdObfuscator idObfuscator;
    private final CustomerMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, IdObfuscator idObfuscator, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.idObfuscator = idObfuscator;
        this.mapper = mapper;
    }

    @Override
    public Page<CustomerListDto> list(Pageable pageable) {
        Page<CustomerEntity> entities = customerRepository.findAll(pageable);
        return entities.map(mapper::toListDto);
    }

    @Override
    public CustomerDetailDto detail(String id) {
        CustomerEntity entity = customerRepository.findById(idObfuscator.decode(id)).orElse(null);

        if (entity == null){
            throw new EntityNotFoundException(id + " not found");
        }

        return mapper.toDetailDto(entity);
    }

    @Override
    public ApiResponseDto<CustomerResponseDto> create(CustomerCreateDto customerCreateDto) {
        CustomerEntity entity = mapper.toEntity(customerCreateDto);
        CustomerEntity newEntity = customerRepository.save(entity);
        return ApiResponseDto.<CustomerResponseDto>builder()
                .message("created new customer")
                .response(mapper.toResponseDto(newEntity))
                .success(true)
                .build();
    }

    @Override
    public ApiResponseDto<CustomerResponseDto> update(CustomerUpdateDto customerUpdateDto) {
        CustomerEntity entity = mapper.toEntity(customerUpdateDto);
        CustomerEntity updatedEntity = customerRepository.save(entity);
        return ApiResponseDto.<CustomerResponseDto>builder()
                .message("updated customer")
                .response(mapper.toResponseDto(updatedEntity))
                .success(true)
                .build();
    }

    @Override
    public ApiResponseDto<CustomerResponseDto> delete(String id) {
        CustomerEntity entity = customerRepository.findById(idObfuscator.decode(id)).orElse(null);

        if (entity == null){
            throw new EntityNotFoundException(id + " not found");
        }

        customerRepository.delete(entity);
        return ApiResponseDto.<CustomerResponseDto>builder()
                .response(mapper.toResponseDto(entity))
                .success(true)
                .message("successfully deleted")
                .build();
    }
}
