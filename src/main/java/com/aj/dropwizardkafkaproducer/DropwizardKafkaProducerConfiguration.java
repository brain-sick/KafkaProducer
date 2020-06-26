package com.aj.dropwizardkafkaproducer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DropwizardKafkaProducerConfiguration extends Configuration {

    @JsonProperty
    private String appName;
    private  String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}