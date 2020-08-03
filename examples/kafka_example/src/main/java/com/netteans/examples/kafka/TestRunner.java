package com.netteans.example.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestRunner {

    //Following code is only used as a test harness.

    //Following source is used as test producer.
    @EnableBinding(TestSource.class)//输出到 TestSource @Output(topic) Channel的流
    static class TestProducer {
        private final Logger logger = LoggerFactory.getLogger(TestProducer.class);

        private AtomicBoolean semaphore = new AtomicBoolean(true);

        private String[] randomWords = new String[]{"foo", "bar", "foobar", "baz", "fox"};
        private Random random = new Random();

        @Bean
        @InboundChannelAdapter(channel = TestSource.OUTPUT, poller = @Poller(fixedDelay = "1000"))
        public MessageSource<String> sendTestData() {
            return () -> {
                int idx = random.nextInt(5);
                logger.info("send word " + randomWords[idx]);
                return new GenericMessage<>(randomWords[idx]);
            };
        }
    }

    //Following sink is used as test consumer for the above processor. It logs the data received through the processor.
    @EnableBinding(TestSink.class) //绑定TestSink @Input(topic) Channel的流消息输入
    static class TestConsumer {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        @StreamListener(TestSink.INPUT)
        public void receive(String data) {
            logger.info("Data received..." + data);
        }
    }

    interface TestSink {

        String INPUT = "input1";

        @Input(INPUT)
        SubscribableChannel input1();

    }

    interface TestSource {

        String OUTPUT = "output1";

        @Output(TestSource.OUTPUT)
        MessageChannel output();

    }
}
