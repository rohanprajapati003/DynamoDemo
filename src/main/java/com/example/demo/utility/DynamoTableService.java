package com.example.demo.utility;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.example.demo.config.DynamoDBConfig;
import com.example.demo.model.Bank;
import com.example.demo.model.BankList;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Import({PropertyPlaceholderAutoConfiguration.class, DynamoDBConfig.class})
public class DynamoTableService {

    private final AmazonDynamoDB amazonDynamoDB;

    private final DynamoDBMapper mapper;

    private boolean isTestTableCreated;

    private List<Class> modelClasses;

    @Autowired
    public DynamoTableService(DynamoDBMapper mapper, AmazonDynamoDB amazonDynamoDB) {
        this.mapper = mapper;
        this.amazonDynamoDB = amazonDynamoDB;

        modelClasses = new ArrayList<>();
        modelClasses.add(Person.class);
        modelClasses.add(BankList.class);
        modelClasses.add(Bank.class);

    }

    public void initializeDynamoDbTables() throws Exception {

        for (Class clazz : modelClasses) {
            createTable(clazz);
        }

        listDynamoDbTables().forEach(System.out::println);

    }

    private void createTable(Class clazz) throws InterruptedException {


        CreateTableRequest ctr = mapper
                .generateCreateTableRequest(clazz)
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

        isTestTableCreated = TableUtils.createTableIfNotExists(amazonDynamoDB, ctr);

        if (isTestTableCreated) {
            System.out.println("Created DynamoDB table for " + ctr.getTableName());
        } else {
            System.out.println("Table already exists for " + ctr.getTableName());
            return;
        }

        TableUtils.waitUntilActive(amazonDynamoDB, ctr.getTableName());

        System.out.println("Table " + ctr.getTableName() + " is active");
    }


    private List<String> listDynamoDbTables() {

        ListTablesResult tablesResult = amazonDynamoDB.listTables();

        return new ArrayList<>(tablesResult.getTableNames());
    }

    private void deleteTable(Class clazz) {

        if (isTestTableCreated) {

            DeleteTableRequest dtr = mapper.generateDeleteTableRequest(clazz);
            TableUtils.deleteTableIfExists(amazonDynamoDB, dtr);
            System.out.println("Deleted table " + dtr.getTableName());
        }
    }

    public void deleteDynamoDbTables() {

        for (Class clazz : modelClasses) {
            deleteTable(clazz);
        }
    }


}
