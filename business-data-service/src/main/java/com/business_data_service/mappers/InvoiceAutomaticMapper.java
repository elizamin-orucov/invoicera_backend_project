package com.business_data_service.mappers;

import com.business_data_service.dtos.invoice.InvoiceAutomaticCreateDto;
import com.business_data_service.dtos.invoice.InvoiceAutomaticResponse;
import com.business_data_service.dtos.invoice.InvoiceAutomaticUpdateDto;
import com.business_data_service.models.CustomerEntity;
import com.business_data_service.models.InvoiceAutomaticEntity;
import com.business_data_service.models.ProductEntity;
import com.business_data_service.repositories.CustomerRepository;
import com.business_data_service.repositories.InvoiceAutomaticRepository;
import com.business_data_service.repositories.ProductRepository;
import com.business_data_service.util.IdObfuscator;
import org.springframework.stereotype.Component;

@Component
public class InvoiceAutomaticMapper {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final IdObfuscator idObfuscator;
    private final InvoiceAutomaticRepository invoiceAutomaticRepository;

    public InvoiceAutomaticMapper(CustomerRepository customerRepository, ProductRepository productRepository, IdObfuscator idObfuscator, InvoiceAutomaticRepository invoiceAutomaticRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.idObfuscator = idObfuscator;
        this.invoiceAutomaticRepository = invoiceAutomaticRepository;
    }

    // to dto
    public InvoiceAutomaticResponse toInvoiceAutomaticResponse(InvoiceAutomaticEntity entity){
        InvoiceAutomaticResponse response = new InvoiceAutomaticResponse();
        response.setId(idObfuscator.encode(entity.getId()));
        response.setProductName(entity.getProduct().getProductName());
        response.setCustomerName(entity.getCustomer().getCustomerName());
        response.setSuccess(true);
        response.setMessage("success");
        return response;
    }

    // to entity
    public InvoiceAutomaticEntity toEntity(InvoiceAutomaticCreateDto createDto){
        CustomerEntity customer = findCustomer(idObfuscator.decode(createDto.getCustomerID()));
        ProductEntity product = findProduct(idObfuscator.decode(createDto.getProductID()));
        InvoiceAutomaticEntity entity = new InvoiceAutomaticEntity();
        entity.setCustomer(customer);
        entity.setProduct(product);
        entity.setPrice(createDto.getPrice());
        entity.setDate(createDto.getDate_of_payment());
        return entity;
    }

    public InvoiceAutomaticEntity toEntity(InvoiceAutomaticUpdateDto updateDto){
        InvoiceAutomaticEntity entity = invoiceAutomaticRepository.findById(idObfuscator.decode(updateDto.getProductID())).orElse(null);
        assert entity != null;
        entity.setProduct(findProduct(idObfuscator.decode(updateDto.getProductID())));
        entity.setPrice(updateDto.getPrice());
        entity.setDate(updateDto.getDate_of_payment());
        return entity;
    }


    // private methods
    private CustomerEntity findCustomer(Long ID){
        return customerRepository.findById(ID).orElse(null);
    }

    private ProductEntity findProduct(Long ID){
        return productRepository.findById(ID).orElse(null);
    }


}
