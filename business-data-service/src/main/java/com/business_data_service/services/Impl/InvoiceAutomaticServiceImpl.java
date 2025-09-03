package com.business_data_service.services.Impl;

import com.business_data_service.dtos.invoice.InvoiceAutomaticCreateDto;
import com.business_data_service.dtos.invoice.InvoiceAutomaticResponse;
import com.business_data_service.dtos.invoice.InvoiceAutomaticUpdateDto;
import com.business_data_service.dtos.response.ApiResponseDto;
import com.business_data_service.mappers.InvoiceAutomaticMapper;
import com.business_data_service.models.CustomerEntity;
import com.business_data_service.models.InvoiceAutomaticEntity;
import com.business_data_service.models.ProductEntity;
import com.business_data_service.repositories.CustomerRepository;
import com.business_data_service.repositories.InvoiceAutomaticRepository;
import com.business_data_service.repositories.ProductRepository;
import com.business_data_service.services.InvoiceAutomaticService;
import com.business_data_service.util.IdObfuscator;
import org.springframework.stereotype.Service;

@Service
public class InvoiceAutomaticServiceImpl implements InvoiceAutomaticService {

    private final InvoiceAutomaticRepository repository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final IdObfuscator idObfuscator;
    private final InvoiceAutomaticMapper mapper;

    public InvoiceAutomaticServiceImpl(InvoiceAutomaticRepository repository, CustomerRepository customerRepository, ProductRepository productRepository, IdObfuscator idObfuscator, InvoiceAutomaticMapper mapper) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.idObfuscator = idObfuscator;
        this.mapper = mapper;
    }

    private CustomerEntity getCustomer(String customerID){
        return customerRepository.findById(idObfuscator.decode(customerID)).orElse(null);
    }

    private ProductEntity getProduct(String productID){
        return productRepository.findById(idObfuscator.decode(productID)).orElse(null);
    }


    @Override
    public ApiResponseDto<InvoiceAutomaticResponse> createInvoice(InvoiceAutomaticCreateDto request) {

        InvoiceAutomaticEntity entity = mapper.toEntity(request);
        InvoiceAutomaticEntity savedEntity = repository.save(entity);

        InvoiceAutomaticResponse response = mapper.toInvoiceAutomaticResponse(savedEntity);

        return ApiResponseDto.<InvoiceAutomaticResponse>builder()
                .success(true)
                .message("success create")
                .response(response)
                .build();
    }

    @Override
    public ApiResponseDto<InvoiceAutomaticResponse> updateInvoice(InvoiceAutomaticUpdateDto request) {
        InvoiceAutomaticEntity entity = mapper.toEntity(request);
        repository.save(entity);

        InvoiceAutomaticResponse response = mapper.toInvoiceAutomaticResponse(entity);

        return ApiResponseDto.<InvoiceAutomaticResponse>builder()
                .success(true)
                .message("success update")
                .response(response)
                .build();
    }
}
