package com.serverless.pirates.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDateTime;

@DynamoDbBean
public class Pirate {
    
    private String pirateId;
    private String createdAt;
    private String name; 
    private String age;
    private String crew;

    public Pirate() {}

    public Pirate(String pirateId, String age, String crew, String name) {
        this.age = age;
        this.name = name;
        this.crew = crew;
        this.pirateId = pirateId;
        this.createdAt = LocalDateTime.now().toString();
    }

    @DynamoDbPartitionKey
    public String getPirateId() {
        return pirateId;
    }

    public String getCreatedAt() { return createdAt; }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getCrew() {
        return crew;
    }

    public void setPirateId(String pirateId) { this.pirateId = pirateId;}

    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public void setName(String name) { this.name = name; }

    public void setAge(String age) { this.age = age; }

    public void setCrew(String crew) { this.crew = crew; }
}
