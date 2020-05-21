package com.netteans.examples.thrift.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netteans.examples.thrift.BootStrap;
import com.netteans.examples.thrift.ExampleConfigure;
import com.netteans.examples.thrift.ExampleClient;
import com.netteans.examples.thrift.domain.ExpDomain;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStrap.class)
public class ExampleTestCase {

    private final Logger logger = LoggerFactory.getLogger(ExampleTestCase.class);

    private String host;
    private ExampleClient exampleClient;
    private int port;

//    @Autowired
//    private ObjectMapper objectMapper;

    @Autowired
    private ExampleConfigure exampleConfigure;

    @Before
    public void before() {
        host = "127.0.0.1";
        port = 8080;
        exampleClient = new ExampleClient(host, port);
    }

    @Test
    public void test() {
        ExpDomain quest = exampleClient.quest("error");
        try {
            logger.debug("case in testcase {}", exampleConfigure.objectMapper().writeValueAsString(quest));
            logger.debug("{}", "justice");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ntest(){
    }
}
