package com.serverless.pirates.repository;

import com.serverless.pirates.config.DynamoDbConfig;
import com.serverless.pirates.model.Pirate;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Optional;

public class PiratesRepository {

    private final DynamoDbTable<Pirate> table;

    public PiratesRepository() {
        this.table = DynamoDbConfig
                .enhancedClient()
                .table(System.getenv("PIRATES_TABLE_NAME"), TableSchema.fromBean(Pirate.class));
    }

    //Create the pirate
    public void save(Pirate pirate) {
        table.putItem(pirate);
    }

    //Get pirate by id
    public Optional<Pirate> findById(String pirateId) {
        Key key = Key.builder().partitionValue(pirateId).build();
        return Optional.ofNullable(table.getItem(key));
    }

    //Get all pirates
    public List<Pirate> findAll() {
        return table
                .scan()
                .items().stream()
                .toList();
    }

    //Delete the pirate by id
    public void delete(String pirateId) {
        Key key = Key.builder().partitionValue(pirateId).build();
        table.deleteItem(key);
    }
}
