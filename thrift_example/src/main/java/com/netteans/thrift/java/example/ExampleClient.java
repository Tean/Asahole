package com.netteans.thrift.java.example;

import com.netteans.thrift.java.example.domain.ExpDomain;
import com.netteans.thrift.java.example.domain.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleClient<Q> {

    private final Logger logger = LoggerFactory.getLogger(ExampleClient.class);
    private final String host;
    private final int port;

    public ExampleClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public ExpDomain quest(Q param) {

        TTransport transport = null;
        try {
            transport = new TFramedTransport(new TSocket(this.host, port));
            TProtocol protocol = new TJSONProtocol(transport);
            HelloService.Client client = new HelloService.Client(protocol);
            transport.open();
            int question = client.question("test");
            logger.debug("q is {}", question);
            if (question > 0) {
                ExpDomain answer = client.answer();
                return answer;
            }
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (transport != null)
                transport.close();
        }
        return null;
    }
}
