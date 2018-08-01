package com.test.kafka.demo.config;

/**
 * @author kapilkapri on 24/07/18 - 1:44 PM
 * @project demo
 */

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kapilkapri on 24/07/18 - 12:21 AM
 * @project interfaces
 */

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;
    @Value("${kafka.group.id}")
    private String groupId;
    @Value("${kafka.offset.reset.config}")
    private String offsetResetConfig;
    @Value("${kafka.enable.auto.commit}")
    private boolean enableAutoCommit;
    @Value("${kafka.key.deserializer}")
    private String keyDeserializer;
    @Value("${kafka.value.deserializer}")
    private String valueDeserializer;
    @Value("${kafka.auto.commit.interval.ms}")
    private String autoCommitIntervalMsConfig;
/*    @Value("${kafka.session.timeout}")
    private boolean sessionTimeoutMsConfig;
    */


    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        /***
         specifies a unique string that identifies the consumer group this consumer belongs to.
         */

        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        /***
         specifies what to do when there is no initial offset in Kafka or if the current offset does not exist any more on the server
         */
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetResetConfig);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitIntervalMsConfig);
        //props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeoutMsConfig);
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new StringDeserializer());
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
       // factory.setConcurrency(15);
        return factory;
    }
}