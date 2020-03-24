package com.netteans.thrift.java.example;

import com.netteans.thrift.java.example.domain.HelloService;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class ExampleServer {

    private Logger logger = LoggerFactory.getLogger(ExampleServer.class);
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,
            4,
            100,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>());

    private TNonblockingServerSocket transport;
    private TJSONProtocol.Factory protocolFactory = new TJSONProtocol.Factory();
    private HelloService.Processor<HelloService.Iface> processor;
    private TNonblockingServer server;

    public void run(int port) throws TTransportException {
        transport = new TNonblockingServerSocket(port);
        processor = new HelloService.Processor<HelloService.Iface>(new HelloServiceHandler());

        TNonblockingServer.Args args = new TNonblockingServer.Args(transport)
                .processor(processor)
                .protocolFactory(protocolFactory);
        server = new TNonblockingServer(args);
        logger.info("Server running @ {} in thread {}", port, Thread.currentThread().getId());
        server.serve();
    }

    public void asyncRun(int port) {
        executor.execute(() -> {
            try {
                run(port);
            } catch (TTransportException e) {
                e.printStackTrace();
            }
        });
    }
}
