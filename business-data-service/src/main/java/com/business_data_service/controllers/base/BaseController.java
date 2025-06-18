package com.business_data_service.controllers.base;

import com.business_data_service.dtos.response.ApiResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface BaseController<
        EntityDetailDto,
        EntityListDto,
        EntityCreateDto,
        EntityUpdateDto,
        EntityResponseDto
        > {
    ResponseEntity<Page<EntityListDto>> fetchAll(int page, int size);

    default ResponseEntity<EntityDetailDto> fetchById(String id){return null;};

    default ResponseEntity<EntityDetailDto> fetchBySlug(String slug){return null;};

    ResponseEntity<ApiResponseDto<EntityResponseDto>> create(@Valid @RequestBody EntityCreateDto createDto);

    ResponseEntity<ApiResponseDto<EntityResponseDto>> update(@Valid @RequestBody EntityUpdateDto updateDto);

    ResponseEntity<ApiResponseDto<EntityResponseDto>> delete(String id);
}
