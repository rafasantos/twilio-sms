package com.github.rafasantos.twiliosmssample.ui;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.GenericWebApplicationContext;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {
    private final GenericWebApplicationContext applicationContext;

    @GetMapping
    public String get() {
        return "settings-page";
    }
}
