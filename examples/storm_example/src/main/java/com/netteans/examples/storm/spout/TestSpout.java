package com.netteans.exampless.storm.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestSpout extends BaseRichSpout {
    private static final Logger logger = LoggerFactory.getLogger(TestSpout.class);

    private SpoutOutputCollector collector;
    private Character[] words = {'客', '上', '天', '然', '居'};

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        List<Character> cars = new ArrayList<>();
        for (Character c : words) {
            cars.add(c);
        }
        StringBuilder got = new StringBuilder();
        for (; cars.size() > 0; ) {
            Random r = new Random(System.currentTimeMillis());
            if (cars.size() > 1) {
                int index = r.nextInt(cars.size());
                got.append(cars.get(index));
                cars.remove(index);
            } else {
                got.append(cars.get(0));
                cars.remove(0);
            }
        }
        String gstrs = got.toString();
        logger.info("{}", gstrs);
        this.collector.emit("wordsflow", new Values(gstrs));
        Utils.sleep(500);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declareStream("wordsflow", new Fields("words"));
    }
}
