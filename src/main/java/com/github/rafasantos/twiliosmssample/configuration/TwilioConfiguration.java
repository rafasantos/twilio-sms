package com.github.rafasantos.twiliosmssample.configuration;

import com.twilio.http.TwilioRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfiguration {
    @Value("${twilio.authentication.account_sid}")
    private String accountSid;
    @Value("${twilio.authentication.auth_token}")
    private String authToken;

    @Bean
    public TwilioRestClient twilioRestClient() {
        var builder = new TwilioRestClient.Builder(accountSid, authToken);
        return builder.build();
    }
}
