package com.business_data_service.mappers;

import com.business_data_service.dtos.customer.*;
import com.business_data_service.models.CustomerEntity;
import com.business_data_service.util.IdObfuscator;
import com.business_data_service.util.VoenEncryptor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CustomerMapper {
    private final VoenEncryptor encryptor;
    private final IdObfuscator idObfuscator;

    public CustomerMapper(VoenEncryptor encryptor, IdObfuscator idObfuscator) {
        this.encryptor = encryptor;
        this.idObfuscator = idObfuscator;
    }

    // to entity
    public CustomerEntity toEntity(CustomerCreateDto createDto){
        CustomerEntity entity = new CustomerEntity();

//        entity.setCustomerSurname(createDto.getCustomerSurname());
//        entity.setPosition(createDto.getPosition());
//        entity.setInstitution(createDto.getInstitution());
        entity.setCustomerName(createDto.getCustomerName());
        entity.setContact(createDto.getContact());
        entity.setMail(createDto.getMail());
        entity.setTIN(createDto.getTIN());
        entity.setFSCode(createDto.getFSCode());

        return entity;
    }

    public CustomerEntity toEntity(CustomerUpdateDto updateDto){
        CustomerEntity entity = new CustomerEntity();

        entity.setId(idObfuscator.decode(updateDto.getCustomer_id()));
        entity.setCustomerName(updateDto.getCustomerName());
        entity.setContact(updateDto.getContact());
        entity.setFSCode(updateDto.getFSCode());
        entity.setMail(updateDto.getMail());

//        entity.setCustomerSurname(updateDto.getCustomerSurname());
//        entity.setPosition(updateDto.getPosition());
//        entity.setInstitution(updateDto.getInstitution());
        entity.setTIN(updateDto.getTIN());

        return entity;
    }

    // to dto
    public CustomerListDto toListDto(CustomerEntity entity){
        CustomerListDto listDto = new CustomerListDto();

//        String fullName = generateFullName(entity.getCustomerName(), entity.getCustomerSurname());

        listDto.setId(idObfuscator.encode(entity.getId()));
        listDto.setFull_name(entity.getCustomerName());
        listDto.setContact(entity.getContact());
        listDto.setPosition(entity.getPosition());
        listDto.setInstitution(entity.getInstitution());
        listDto.setTIN(entity.getTIN());

        return listDto;
    }

    public CustomerDetailDto toDetailDto(CustomerEntity entity){
        CustomerDetailDto detailDto = new CustomerDetailDto();

        detailDto.setId(idObfuscator.encode(entity.getId()));
        detailDto.setCustomerName(entity.getCustomerName());
        detailDto.setCustomerSurname(entity.getCustomerSurname());
        detailDto.setContact(entity.getContact());
        detailDto.setPosition(entity.getPosition());
        detailDto.setInstitution(entity.getInstitution());
        detailDto.setTIN(entity.getTIN());

        return detailDto;
    }

    public CustomerResponseDto toResponseDto(CustomerEntity entity){
        CustomerResponseDto responseDto = new CustomerResponseDto();

        responseDto.setId(idObfuscator.encode(entity.getId()));
        responseDto.setCustomer_name(entity.getCustomerName());
        responseDto.setCustomer_surname(entity.getCustomerSurname());
        return responseDto;
    }

    private static String generateFullName(String name, String surname) {
        return Stream.of(name, surname)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(" "));
    }


}
