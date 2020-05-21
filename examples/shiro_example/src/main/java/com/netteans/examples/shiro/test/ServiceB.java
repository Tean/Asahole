package com.netteans.examples.shiro.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class ServiceB {

//    @Autowired
    ServiceA serviceA;
}
