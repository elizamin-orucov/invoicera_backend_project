package com.business_data_service.base;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class BaseIdDto {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String uuid) {
        this.id = uuid;
    }
}
