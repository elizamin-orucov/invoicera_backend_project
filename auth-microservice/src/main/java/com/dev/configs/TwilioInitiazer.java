package com.dev.configs;

import com.twilio.Twilio;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class TwilioInitiazer {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitiazer.class);

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioInitiazer(TwilioConfiguration twilioConfiguration){
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );
        LOGGER.info("Twilio initialized ... with account sid {}", twilioConfiguration.getAccountSid());
    }

}