package com.netteans.agent.service;

import com.netteans.agent.LocalThreadCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LocaleService {
    @Autowired
    private MessageSource messageSource;

    Long l;

    @Value("${valid.password.message:id.auth}")
    private String localeTest;

    @Value("${id.locale:0}")
    private Integer localeId;

    @Autowired
    private LocalThreadCounter localThreadCounter;

    public String getLocale() {
        String msg = messageSource.getMessage(localeTest, null, LocaleContextHolder.getLocale());
        return msg;
    }

    public int increase(){
        return localThreadCounter.increase();
    }
}
