package com.example.demo;

import com.example.demo.utility.DynamoTableService;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

public class AbstractIntegrationTest {

    @Autowired
    private DynamoTableService dynamoTableService;

    @Before
    public void setUp() throws Exception {
        dynamoTableService.initializeDynamoDbTables();
    }

    @After
    public void tearDown() {
        //Note: Currently, I am using a local database for running tests and development and I don't want to remove the tables
        dynamoTableService.deleteDynamoDbTables();
    }
}
