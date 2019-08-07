package com.netteans.examples.storm;

import com.netteans.examples.storm.bolt.CountBolt;
import com.netteans.examples.storm.spout.TestSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class BootStrap {
    private static final Logger logger = LoggerFactory.getLogger(BootStrap.class);

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

    @Bean
    ApplicationRunner ar() {
        return args -> {
            logger.info("top start");

            TopologyBuilder topologyBuilder = new TopologyBuilder();
            topologyBuilder.setSpout("wordspout", new TestSpout(), 1);
            topologyBuilder.setBolt("count", new CountBolt(), 2).localOrShuffleGrouping("wordspout", "wordsflow");

            Config conf = new Config();
            conf.setDebug(false);

            if (args.getSourceArgs().length > 0) {
                // 集群模式
                conf.setNumWorkers(2);
                StormSubmitter.submitTopology(args.getSourceArgs()[0], conf, topologyBuilder.createTopology());
            } else {
                LocalCluster cluster = new LocalCluster();
                cluster.submitTopology("word-count", conf, topologyBuilder.createTopology());
                TimeUnit.SECONDS.sleep(10);
            }
        };
    }
}
