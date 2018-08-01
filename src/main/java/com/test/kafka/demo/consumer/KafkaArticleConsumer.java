package com.test.kafka.demo.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.kafka.demo.pojo.ProducerPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author kapilkapri on 24/07/18 - 2:21 PM
 * @project demo
 */

@Service
public class KafkaArticleConsumer<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaArticleConsumer.class);
    private final CountDownLatch latch = new CountDownLatch(1);

    /*   topics = "${kafka.topic.suppress_brand_store},${kafka.topic.article_update}"*/
    @KafkaListener(topics = "#{'${kafka.timeline.topics}'.split(',')}", containerFactory = "kafkaListenerContainerFactory")
    public void receiveMessage(final String jsonMessage, @Header(KafkaHeaders.OFFSET) Integer offset,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ProducerPayload payload = mapper.readValue(jsonMessage, ProducerPayload.class);
        LOGGER.info("\n\n CONSUMER -------->>  Processing topic = {}, partition = {}, offset = {}, payload type {},\n received data= {}",
                topic, partition, offset, payload.getMeta().getAction(), jsonMessage);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}