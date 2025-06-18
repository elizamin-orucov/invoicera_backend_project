package com.business_data_service.services;

import com.business_data_service.dtos.response.ApiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface BaseCRUDService<
        EntityDetailDto, EntityListDto, EntityCreateDto, EntityUpdateDto, EntityResponseDto
        > {

    Page<EntityListDto> list(Pageable pageable);

    default List<EntityListDto> list(){
        return new ArrayList<>();
    };

    EntityDetailDto detail(String id);

    ApiResponseDto<EntityResponseDto> create(EntityCreateDto dto);

    ApiResponseDto<EntityResponseDto> update(EntityUpdateDto dto);

    ApiResponseDto<EntityResponseDto> delete(String id);

}
