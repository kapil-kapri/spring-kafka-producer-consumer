package com.test.kafka.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.kafka.demo.pojo.*;
import com.test.kafka.demo.producer.KafkaArticleProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kapilkapri on 24/07/18 - 6:42 PM
 * @project demo
 */


@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Value("${kafka.topic.article_update}")
    private String topicArticleUpdate;
    @Value("${kafka.topic.suppress_brand_store}")
    private String topicSuppressbrandStore;

    @Autowired
    private KafkaArticleProducer sender;

    @RequestMapping("/greeting")
    public String greeting() throws Exception {
        sendData();
        return template;
    }


    public void sendData() throws Exception {
        Integer brandId = 23;
        Integer jobId = 88923;
        suppressArticleData(jobId, brandId);
        suppressBrandStore(jobId, brandId);
        sendDeltaUpdate(jobId, brandId);
        sendUpsert(jobId, brandId);
    }

    public void suppressArticleData(Integer jobId, Integer key) throws JsonProcessingException {
        ProducerPayload producerPayload;
        List<SuppressArticlePayload> suppressList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            suppressList.add(new SuppressArticlePayload("uid" + i, i));
        }
        producerPayload = new ProducerPayload(suppressList, MetaData.ActionType.SUPPRESS.toString());
        ObjectMapper mapper = new ObjectMapper();
        // java Object to JSON in String
        String jsonInString = mapper.writeValueAsString(producerPayload);
        sender.sendMessage(jobId, topicArticleUpdate, key + "", jsonInString);
    }

    public void suppressBrandStore(Integer jobId, Integer key) throws JsonProcessingException {
        ProducerPayload producerPayload;
        List<SuppressStorePayload> suppressList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            suppressList.add(new SuppressStorePayload(i, Arrays.asList("store1", "store2", "store13")));
        }
        producerPayload = new ProducerPayload(suppressList, MetaData.ActionType.BRAND_STORE_SUPPRESS.toString());
        ObjectMapper mapper = new ObjectMapper();
        //java Object to JSON in String
        String jsonInString = mapper.writeValueAsString(producerPayload);
        sender.sendMessage(jobId, topicSuppressbrandStore, key + "", jsonInString);
    }


    public void sendDeltaUpdate(Integer jobId, Integer key) throws JsonProcessingException {
        ProducerPayload producerPayload;
        List<UpdateArticlePayload> updateArticleList = new ArrayList<>();

        // quantity update
        updateArticleList.add(new UpdateArticlePayload("uid1", 10));

        // price update
        updateArticleList.add(new UpdateArticlePayload("uid2", new ArticlePrice(123.56, 345.67)));
        updateArticleList.add(new UpdateArticlePayload("uid3", new ArticlePrice(123.56, 345.67, (double) 45)));
        updateArticleList.add(new UpdateArticlePayload("uid3", new ArticlePrice(123.56, 345.67, "Dollar", (double) 45)));

        // price  and quantity update
        updateArticleList.add(new UpdateArticlePayload("uid4", 45, new ArticlePrice(123.56, 345.67, "Dollar", (double) 45)));

        producerPayload = new ProducerPayload(updateArticleList, MetaData.ActionType.UPDATE.toString());
        ObjectMapper mapper = new ObjectMapper();
        // java Object to JSON in String
        String jsonInString = mapper.writeValueAsString(producerPayload);
        sender.sendMessage(jobId, topicArticleUpdate, key + "", jsonInString);
    }

    public void sendUpsert(Integer jobId, Integer key) throws JsonProcessingException {
        ProducerPayload producerPayload;
        List<UpsertPayload> updateArticleList = new ArrayList<>();

        Map<String, Object> identifierMap = new HashMap<>();
        identifierMap.put("sku", "SS18AB032DRCYD@WHITE-8");
        identifierMap.put("test", 1);
        identifierMap.put("test2", 1.67);

        Map<String, Object> rawMap = new HashMap<>();
        rawMap.put("size", "XL");
        rawMap.put("store_code", "abc");
        rawMap.put("test1", 2.67);
        rawMap.put("test2", 2.67);

        updateArticleList.add(new UpsertPayload("jhj-mnmn", new Company(2), new Brand("AND", 3),
                new Store("DummyStoreCode"), "itemcode1", "XL",
                identifierMap, rawMap, new ArticlePrice(123.56, 345.67), 8,
                "2016-01-16T07:16:13.000Z"));

        updateArticleList.add(new UpsertPayload("jhj-mnmn-rr", new Company(3), new Brand("AND", 4),
                new Store("DummyStoreCode"), "itemcode2", "XL",
                identifierMap, rawMap, new ArticlePrice(123.56, 345.67), 8,
                "2016-01-16T07:16:13.000Z"));

        producerPayload = new ProducerPayload(updateArticleList, MetaData.ActionType.UPSERT.toString());
        ObjectMapper mapper = new ObjectMapper();
        // java Object to JSON in String
        //String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(producerPayload);
        String jsonInString = mapper.writeValueAsString(producerPayload);
        sender.sendMessage(jobId, topicArticleUpdate, key + "", jsonInString);
    }
}