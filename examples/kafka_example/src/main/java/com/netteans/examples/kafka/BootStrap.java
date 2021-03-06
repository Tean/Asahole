package com.netteans.examples.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Serialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@EnableKafkaStreams
public class BootStrap {

    private static final Logger logger = LoggerFactory.getLogger(BootStrap.class);

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            logger.info("{}", args.getSourceArgs());
        };
    }

    @EnableBinding(KafkaStreamsProcessor.class)
    public static class WordCountProcessorApplication {

        static final String INPUT_TOPIC = "input";
        static final String OUTPUT_TOPIC = "output";
        static final int WINDOW_SIZE_MS = 30000;

        @StreamListener(INPUT_TOPIC)
        @SendTo(OUTPUT_TOPIC)
        public KStream<Bytes, WordCount> process(KStream<Bytes, String> input) {

            return input
                    .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
                    .map((key, value) -> new KeyValue<>(value, value))
                    .groupByKey(Serialized.with(Serdes.String(), Serdes.String()))
                    .windowedBy(TimeWindows.of(WINDOW_SIZE_MS))
                    .count(Materialized.as("WordCounts-1"))
                    .toStream()
                    .map((key, value) -> new KeyValue<>(null, new WordCount(key.key(), value, new Date(key.window().start()), new Date(key.window().end()))));
        }
    }

    static class WordCount {

        private String word;

        private long count;

        private Date start;

        private Date end;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("WordCount{");
            sb.append("word='").append(word).append('\'');
            sb.append(", count=").append(count);
            sb.append(", start=").append(start);
            sb.append(", end=").append(end);
            sb.append('}');
            return sb.toString();
        }

        WordCount() {

        }

        WordCount(String word, long count, Date start, Date end) {
            this.word = word;
            this.count = count;
            this.start = start;
            this.end = end;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        public Date getStart() {
            return start;
        }

        public void setStart(Date start) {
            this.start = start;
        }

        public Date getEnd() {
            return end;
        }

        public void setEnd(Date end) {
            this.end = end;
        }
    }
}
