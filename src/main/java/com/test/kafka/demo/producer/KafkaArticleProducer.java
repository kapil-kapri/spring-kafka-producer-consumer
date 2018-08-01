package com.test.kafka.demo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;


/**
 * @author kapilkapri on 24/07/18 - 1:38 PM
 * @project demo
 */

@Service
public class KafkaArticleProducer {


    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaArticleProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaArticleProducer(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(final Integer jobId, final String topic, final String key, final String message) {

        kafkaTemplate.send(topic, key, message)
                .addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                    @Override
                    public void onSuccess(final SendResult<String, String> result) {
                        LOGGER.info("\n\nPRODUCER ---->>>  sent message='{}' with offset={} to partition={} Topic  name = {}",
                                message.toString(),
                                result.getRecordMetadata().offset(), result.getRecordMetadata().partition(),
                                result.getRecordMetadata().topic());

                    }

                    @Override
                    public void onFailure(final Throwable exc) {
                        LOGGER.error("\n\nFailure ---->>> unable to send message='{}'", message, exc);
                        sendFailedDataToDb(jobId, topic, key, message);
                    }
                });

    }

    private void sendFailedDataToDb(Integer jobId, String topic, String key, String message) {
    }
}
