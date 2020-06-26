package com.aj.dropwizardkafkaproducer.service;

import com.aj.dropwizardkafkaproducer.domain.KafkaProduced;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    public String sendMessageToTopic(String topicName, KafkaProduced message) {
        logger.debug("Entering KafkaProducerService.sendMessageToTopic Method");
        String status;
        try {
            final Producer<String, KafkaProduced> producer;
            final Properties props = new Properties();
            //localhost:9092
            props.put("bootstrap.servers", "kafka-1b-us-east-1.egdp-test.aws.away.black:9092");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "com.aj.dropwizardkafkaproducer.utility.kafkaProducedSerializer");
            producer = new KafkaProducer<>(props);

            producer.send(new ProducerRecord<String,KafkaProduced>(topicName, message));
            System.out.println(message);
            producer.close();
            status = "success";
            logger.info("status is: {}", status);
            logger.info("Leaving KafkaProducerService.sendMessageToTopic Method");
        } catch (Exception e) {
            logger.error("Exception: Error sending data to topic "+ e.getMessage(),
                    topicName);
            status = "error";
            logger.error("status is: {}", status);
            return status;
        }
        return status;
    }
}