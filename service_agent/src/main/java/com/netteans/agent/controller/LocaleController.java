package com.netteans.agent.controller;

import com.netteans.agent.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locale")
public class LocaleController {

    @Autowired
    LocaleService localeService;

    @GetMapping("/")
    public String getLocale() {
        return localeService.getLocale();
    }
}
