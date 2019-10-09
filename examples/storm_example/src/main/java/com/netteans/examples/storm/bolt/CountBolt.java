package com.netteans.examples.storm.bolt;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

public class CountBolt extends BaseBasicBolt {
    private static final Logger logger = LoggerFactory.getLogger(CountBolt.class);

    private final Map<String, Integer> countMap = new TreeMap<>();

    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String word = tuple.getStringByField("words");
        if (countMap.get(word) == null) {
            countMap.put(word, 1);
        } else {
            int count = countMap.get(word);
            countMap.put(word, count + 1);
        }

        for (String wkey : countMap.keySet()) {
            logger.info("{} : {}", wkey, countMap.get(wkey));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    }

    @Override
    public void cleanup() {
        super.cleanup();
        for (String wkey : countMap.keySet()) {
            logger.info("{} : {}", wkey, countMap.get(wkey));
        }
    }
}
