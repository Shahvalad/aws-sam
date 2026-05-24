package com.serverless.pirates.config;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DynamoDbConfig {

    private static final DynamoDbClient dynamoDbClient =
            DynamoDbClient.create();

    private static final DynamoDbEnhancedClient enhancedClient =
            DynamoDbEnhancedClient
                    .builder()
                    .dynamoDbClient(dynamoDbClient)
                    .build();

    public static DynamoDbEnhancedClient enhancedClient() {
        return enhancedClient;
    }
}
