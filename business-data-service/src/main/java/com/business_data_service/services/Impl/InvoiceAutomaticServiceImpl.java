package com.business_data_service.services.Impl;

import com.business_data_service.dtos.invoice.InvoiceAutomaticRequest;
import com.business_data_service.dtos.invoice.InvoiceAutomaticResponse;
import com.business_data_service.dtos.response.ApiResponseDto;
import com.business_data_service.models.CustomerEntity;
import com.business_data_service.models.InvoiceAutomaticEntity;
import com.business_data_service.models.ProductEntity;
import com.business_data_service.repositories.CustomerRepository;
import com.business_data_service.repositories.InvoiceAutomaticRepository;
import com.business_data_service.repositories.ProductRepository;
import com.business_data_service.services.InvoiceAutomaticService;
import com.business_data_service.util.IdObfuscator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class InvoiceAutomaticServiceImpl implements InvoiceAutomaticService {

    private final InvoiceAutomaticRepository repository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final IdObfuscator idObfuscator;

    public InvoiceAutomaticServiceImpl(InvoiceAutomaticRepository repository, CustomerRepository customerRepository, ProductRepository productRepository, IdObfuscator idObfuscator) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.idObfuscator = idObfuscator;
    }

    private CustomerEntity getCustomer(String customerID){
        return customerRepository.findById(idObfuscator.decode(customerID)).orElse(null);
    }

    private ProductEntity getProduct(String productID){
        return productRepository.findById(idObfuscator.decode(productID)).orElse(null);
    }


    @Override
    public ApiResponseDto<InvoiceAutomaticResponse> createInvoice(InvoiceAutomaticRequest request) {
        InvoiceAutomaticEntity invoice = new InvoiceAutomaticEntity();

        CustomerEntity customerEntity = getCustomer(request.getCustomerID());

        ProductEntity productEntity = getProduct(request.getProductID());


        if (productEntity == null | customerEntity == null){
            throw new EntityNotFoundException("invalid ID");
        }

        invoice.setCustomer(customerEntity.getCustomerName());
        invoice.setProduct(productEntity.getProductCode());
        invoice.setPrice(request.getPrice());
        invoice.setDate(request.getDate());

        InvoiceAutomaticResponse response = new InvoiceAutomaticResponse();

        InvoiceAutomaticEntity savedInvoice = repository.save(invoice);

        response.setId(idObfuscator.encode(savedInvoice.getId()));
        response.setCustomerName(customerEntity.getCustomerName());
        response.setProductName(productEntity.getProductName());
        response.setSuccess(true);
        response.setMessage("success");

        return ApiResponseDto.<InvoiceAutomaticResponse>builder()
                .success(true)
                .message("success create")
                .response(response)
                .build();
    }
}
