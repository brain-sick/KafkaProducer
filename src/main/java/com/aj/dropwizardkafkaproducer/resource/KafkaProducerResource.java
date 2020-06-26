package com.aj.dropwizardkafkaproducer.resource;

import com.aj.dropwizardkafkaproducer.domain.KafkaProduced;
import com.aj.dropwizardkafkaproducer.service.KafkaProducerService;

import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/kafka")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "kafka", description = "Kafka Resource for sending messages to Kafka Producer")
public class KafkaProducerResource {

    static String topic = "test";

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerResource.class);

    @POST
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendMessageToTopic(KafkaProduced kafkaProduced) throws Exception {
        logger.info("Request received is: " + kafkaProduced );
        Map<String, String> response = new HashMap<>();
        KafkaProducerService kafkaProducerService = new KafkaProducerService();
        System.out.println(topic);
        String status = kafkaProducerService.sendMessageToTopic(topic,kafkaProduced);
        if("success".equalsIgnoreCase(status)) {
            response.put("Status is: " + status, "Message has been sent to topic : " + topic);
        }
        else{
            response.put("Status is: " + status, "Error in sending Message to topic : " + topic);
        }
        return Response.ok(response).build();
    }
}