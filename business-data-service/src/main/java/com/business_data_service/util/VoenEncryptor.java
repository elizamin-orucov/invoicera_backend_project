package com.business_data_service.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;

@Component
public class VoenEncryptor {

    private final String secretKey;
    private final String algorithm;

    public VoenEncryptor(
            @Value("${app.secret-key}") String secretKey,
            @Value("${app.algorithm}") String algorithm
    ) {
        if (secretKey.length() != 16) {
            throw new IllegalArgumentException("SECRET_KEY must be 16 characters!");
        }
        this.secretKey = secretKey;
        this.algorithm = algorithm;
    }

    public String encrypt(String voen) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(voen.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("VOEN not encrypted!", e);
        }
    }

    public String decrypt(String encryptedVoen) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedVoen));
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("VOEN not decrypted!", e);
        }
    }
}


