package com.business_data_service.util;

import java.util.Random;

public class RandomCodeGenerator {

    public static String generateRandomCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        sb.append(random.nextInt(9) + 1);

        for (int i = 1; i < length; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}

