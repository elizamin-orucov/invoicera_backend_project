package com.dev.service.impl.otp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.dev.service.impl.otp.HashUtil.md5Hash;
import static com.dev.service.impl.otp.RandomCodeGenerate.generateNumberCode;

@Component
public class SmsPayloadBuilder {

    @Value("${sms.login}")
    private static String SMS_LOGIN;

    @Value("${sms.sender}")
    private static String SMS_SENDER;

    @Value("${sms.scheduled}")
    private static String SMS_SCHEDULED;

    @Value("${sms.unicode}")
    private static String SMS_UNICODE;


//    private static String SMS_SENDER_URL = "https://apps.lsim.az/quicksms/v1/smssender";
    @Value("${sms.password.raw}")
    private static String SMS_PASSWORD;

    private static String WEBSITE_DOMAIN = "telef10.az";

    private static String message = "Sizin birdəfəlik şifrəniz {otp_code}";

    public static Map<String, String> buildSmsPayload(String phoneNumber, String otp_code) {

        SMS_PASSWORD = md5Hash(SMS_PASSWORD);
//        String otp_code = generateNumberCode(8);
        message = message.replace("{otp_code}", otp_code);


        String trimmedPhoneNumber = phoneNumber.substring(1);

        String combined_string_of_key = SMS_PASSWORD + SMS_LOGIN + message + trimmedPhoneNumber + SMS_SENDER;


        String key = md5Hash(combined_string_of_key);

        Map<String, String> smsPayload = new HashMap<>();
        smsPayload.put("login", SMS_LOGIN);
        smsPayload.put("key", key);
        smsPayload.put("msisdn", trimmedPhoneNumber);
        smsPayload.put("text", message);
        smsPayload.put("sender", SMS_SENDER);
        smsPayload.put("scheduled", SMS_SCHEDULED);
        smsPayload.put("unicode", SMS_UNICODE);

        return smsPayload;
    }
}