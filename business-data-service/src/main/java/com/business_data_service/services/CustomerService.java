package com.business_data_service.services;

import com.business_data_service.dtos.customer.*;

public interface CustomerService extends BaseCRUDService<
        CustomerDetailDto, CustomerListDto, CustomerCreateDto, CustomerUpdateDto, CustomerResponseDto
        >{
}
