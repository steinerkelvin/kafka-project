package com.ufes.patricia.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo {
    public static void main(String[] args) {
        String BootstrapServer = "localhost:9092";
        String Topic = "third-topic";

        Logger logger = LoggerFactory.getLogger(ConsumerDemo.class.getName());

        //create properties
        Properties prop = new Properties();

        prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BootstrapServer);
        prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "sei-la");
        prop.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);

        //subscribe consumer to topic(s) - Arrays.asList(Topic)
        //consumer.subscribe(Collections.singleton(Topic));
        consumer.subscribe(Arrays.asList(Topic));

        while(true){

            ConsumerRecords<String, String> records= consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records)
                logger.info("Key: " + record.key() + " Value: " + record.value() + " Partition : " + record.partition());

        }
    }
}
