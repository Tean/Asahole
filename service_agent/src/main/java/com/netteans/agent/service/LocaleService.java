package com.netteans.agent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LocaleService {
    @Autowired
    private MessageSource messageSource;

    @Value("${valid.password.message:id.auth}")
    private String localeTest;

    @Value("${id.locale:0}")
    private Integer localeId;

    public String getLocale() {
        String msg = messageSource.getMessage(localeTest, null, LocaleContextHolder.getLocale());
        return msg;
    }
}
