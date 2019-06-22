package com.netteans.example.elk;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ElasticsearchConfig {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchConfig.class);

    @Value("${elasticsearch.cluster.address}")
    private String clusterAddress;

    /**
     * Bean name default  函数名字
     *
     * @return
     */
    @Bean
    public RestHighLevelClient restClient() {
        logger.info("es rest client init");
        List<Node> nodes = new ArrayList<>();
        String[] clusterAddresses = clusterAddress.split(",");
        for (String clusterAddress : clusterAddresses) {
            String[] hap = clusterAddress.split(":");
            if(hap.length!=2) continue;
            HttpHost httpHost = new HttpHost("localhost", 9200, "http");
            Node node = new Node(httpHost);
            nodes.add(node);
        }

        return new RestHighLevelClient(RestClient.builder(nodes.toArray(new Node[0])));
    }
}