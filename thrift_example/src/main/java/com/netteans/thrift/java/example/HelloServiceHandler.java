package com.netteans.thrift.java.example;

import com.netteans.thrift.java.example.domain.ExpDomain;
import com.netteans.thrift.java.example.domain.HelloService;
import org.apache.thrift.TException;

public class HelloServiceHandler implements HelloService.Iface {
    private String question;

    public int question(String param) throws TException {
        if (param != null && !param.equalsIgnoreCase("")) {
            return 1;
        }
        return 0;
    }

    public ExpDomain answer() throws TException {
        if (!question.equalsIgnoreCase("quit")) {
            return new ExpDomain((byte) 0x01);
        }
        return new ExpDomain((byte) 0x00);
    }
}
