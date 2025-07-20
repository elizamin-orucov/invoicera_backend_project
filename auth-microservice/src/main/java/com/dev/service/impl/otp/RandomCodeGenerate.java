package com.dev.service.impl.otp;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class RandomCodeGenerate {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String LETTERS_AND_NUMBERS = LETTERS + NUMBERS;
    private static final SecureRandom random = new SecureRandom();

    public static String generateLetterCode(int length) {
        return generateCode(LETTERS, length);
    }

    public static String generateNumberCode(int length) {
        return generateCode(NUMBERS, length);
    }

    public static String generateAlphaNumericCode(int length) {
        return generateCode(LETTERS_AND_NUMBERS, length);
    }

    private static String generateCode(String source, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(source.charAt(random.nextInt(source.length())));
        }
        return sb.toString();
    }
}

