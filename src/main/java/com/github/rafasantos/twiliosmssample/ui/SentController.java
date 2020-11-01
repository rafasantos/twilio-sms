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

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/sent")
@AllArgsConstructor
@Slf4j
public class SentController {
    private final AccountService accountService;
    private final SmsService smsService;

    @GetMapping
    public String get(@RequestParam("fromPhoneNumber") Optional<String> fromPhoneNumber, Model model) {
        try {
            if (fromPhoneNumber.isPresent()) {
                List<Message> messages = smsService.messagesSent(fromPhoneNumber.get());
                model.addAttribute("messagesSent", messages);
            }
        } catch (Exception e) {
            log.error("Error when sending message: {}", e.getMessage(), e);
            model.addAttribute("error", "Error when sending message. " + e.getMessage());
        }

        Set<String> phoneNumbers = accountService.findAllPhoneNumbers();
        model.addAttribute("phoneNumbers", phoneNumbers);
        return "sent-page";
    }
}
