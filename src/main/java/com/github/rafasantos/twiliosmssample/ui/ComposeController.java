package com.github.rafasantos.twiliosmssample.ui;

import com.github.rafasantos.twiliosmssample.account.AccountService;
import com.github.rafasantos.twiliosmssample.sms.SmsService;
import com.twilio.rest.api.v2010.account.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping("/compose")
@AllArgsConstructor
@Slf4j
public class ComposeController {
    private final AccountService accountService;
    private final SmsService smsService;

    @PostMapping
    public String post(
            @RequestParam("fromPhoneNumber") String fromPhoneNumber,
            @RequestParam("toPhoneNumber") String toPhoneNumber,
            @RequestParam("smsMessage") String smsMessage,
            Model model) {
        try {
            Message message = smsService.sendSms(toPhoneNumber, fromPhoneNumber, smsMessage);
            log.info("SMS message sent; MessageSid: {}", message.getSid());
            model.addAttribute("success", "Message sent. MessageSid: " + message.getSid());
        } catch (Exception e) {
            log.error("Error when sending message: {}", e.getMessage(), e);
            model.addAttribute("error", "Error when sending message. " + e.getMessage());
        }
        Set<String> phoneNumbers = accountService.findAllPhoneNumbers();
        model.addAttribute("phoneNumbers", phoneNumbers);
        return "compose-page";
    }

    @GetMapping
    public String get(Model model) {
        Set<String> phoneNumbers = accountService.findAllPhoneNumbers();
        model.addAttribute("phoneNumbers", phoneNumbers);
        return "compose-page";
    }
}
