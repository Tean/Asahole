package com.netteans.agent.controller;

import com.netteans.agent.LocalThreadCounter;
import com.netteans.agent.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locale")
public class LocaleController {
    private int gcount = 0;

    @Autowired
    LocaleService localeService;

    @GetMapping(value = {"", "/**"})
    public String getLocale() {
        System.out.println(localeService.toString() + " -> " + localeService.increase());
        System.out.println(gcount++);
        return localeService.getLocale();
    }
}
