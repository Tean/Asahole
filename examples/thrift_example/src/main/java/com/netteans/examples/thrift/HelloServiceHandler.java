package com.netteans.examples.thrift;

import com.netteans.examples.thrift.domain.HelloService;
import com.netteans.examples.thrift.domain.ExpDomain;
import org.apache.thrift.TException;

public class HelloServiceHandler implements HelloService.Iface {
    private String question;

    public int question(String param) throws TException {
        if (param != null && !param.equalsIgnoreCase("")) {
            question = param;
            return 1;
        }
        return 0;
    }

    public ExpDomain answer() throws TException {
        ExpDomain expDomain;
        String message = "no error";
        if (question.equalsIgnoreCase("quit")) {
            expDomain = new ExpDomain((byte) 0x00);
            expDomain.domainString = "exit";
            return expDomain;
        } else if (question.equalsIgnoreCase("error")) {
            message = "fatal error";
            throw new TException("Error test");
        }
        expDomain = new ExpDomain((byte) 0x01);
        expDomain.domainString = message;

        return expDomain;
    }
}
