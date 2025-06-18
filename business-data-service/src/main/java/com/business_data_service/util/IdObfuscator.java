package com.business_data_service.util;

import org.hashids.Hashids;
import org.springframework.stereotype.Component;

@Component
public class IdObfuscator {

    private final Hashids hashids;

    public IdObfuscator() {
        this.hashids = new Hashids("my-salt-secret", 8);
    }

    public String encode(Long id) {
        return hashids.encode(id);
    }

    public Long decode(String encodedId) {
        long[] decoded = hashids.decode(encodedId);
        if (decoded.length == 0) {
            throw new IllegalArgumentException("Invalid encoded ID: " + encodedId);
        }
        return decoded[0];
    }
}

