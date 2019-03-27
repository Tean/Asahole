package com.netteans.thrift.java.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ExampleConfigure {
    private static int port;

    private ISet setEvent = new ISet() {
        public void valueChange(int oldValue, int newValue) {
            System.out.println(oldValue + " => " + newValue);
        }
    };

    public static int getPort() {
        return ExampleConfigure.port;
    }

    @Value("${thrift.port}")
    public void setPort(int port) {
        if (this.setEvent != null) {
            this.setEvent.valueChange(ExampleConfigure.port, port);
        }
        ExampleConfigure.port = port;
    }


}
