package com.github.rafasantos.twiliosmssample.configuration;

import com.twilio.http.TwilioRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfiguration {
    @Value("${twilio.authentication.account_sid:UNKNOWN_TWILIO_ACCOUNT_SID}")
    private String accountSid;
    @Value("${twilio.authentication.auth_token:UNKNOWN_TWILIO_AUTH_TOKEN}")
    private String authToken;

    @Bean
    public TwilioRestClient twilioRestClient() {
        var builder = new TwilioRestClient.Builder(accountSid, authToken);
        return builder.build();
    }
}
