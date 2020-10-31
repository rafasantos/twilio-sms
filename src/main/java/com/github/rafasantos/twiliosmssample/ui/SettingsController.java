package com.github.rafasantos.twiliosmssample.ui;

import com.twilio.http.TwilioRestClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.GenericWebApplicationContext;

@Controller
@RequestMapping("/settings")
@AllArgsConstructor
public class SettingsController {
    private final GenericWebApplicationContext applicationContext;

    @PostMapping
    public String post(@RequestParam("account_sid") String account_sid,
                       @RequestParam("auth_token") String auth_token) {
        // TODO: Create a TwilioRestClient factory instead of removing/registering new spring beans
        TwilioRestClient.Builder builder = new TwilioRestClient.Builder(account_sid, auth_token);
        TwilioRestClient newTwilioRestClient = builder.build();
        applicationContext.removeBeanDefinition("twilioRestClient");
        applicationContext.registerBean("twilioRestClient", TwilioRestClient.class, () -> newTwilioRestClient);
        return "settings-page";
    }

    @GetMapping
    public String get() {
        return "settings-page";
    }
}
