package com.ufes.patricia.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {

    public static void main(String[] args) {

        String BootstrapServer = "localhost:9092";

        //create properties
        Properties prop = new Properties();

        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BootstrapServer);
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //criar um produtor
        //tipos de key e value
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        //Criar um producer record
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("third-topic", "ola");

        //enviar data
        producer.send(record);

        producer.flush();
   }
}
