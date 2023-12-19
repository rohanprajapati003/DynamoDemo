package com.example.demo.repo;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
@Component
public class PersonRepository  {

    @Autowired
    private com.example.demo.utility.converter converter;
    @Autowired
    private DynamoDBMapper                     dynamoDBMapper;

    public Person getPersonById(String id){

        System.out.println("Repo method called");
        Map<String, AttributeValue> eav = new HashMap<>();

        eav.put(":id",new AttributeValue().withS(id));

        DynamoDBQueryExpression<Person> queryExpression = new DynamoDBQueryExpression<Person>()
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(eav);

        List<Person> results = dynamoDBMapper.query(Person.class, queryExpression);
        if (!CollectionUtils.isEmpty(results)) {
            return results.get(0);
        } else {
            return null;
        }

    }


    public Person savePerson(Person person) {
        dynamoDBMapper.save(person);
        return person;
    }

    public List<Person> getPersonList(){

        PaginatedScanList<Person> scan = dynamoDBMapper.scan(Person.class, new DynamoDBScanExpression());
        return scan.stream().toList();

    }


    public void deletePersonById(String personId) {

        Person person = getPersonById(personId);
        dynamoDBMapper.delete(person);

    }

    public int getCountByPersonId(String id) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":id", new AttributeValue().withS(id));
        DynamoDBQueryExpression<Person> queryExpression = new DynamoDBQueryExpression<Person>()
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(eav);
        int clientCount = dynamoDBMapper.count(Person.class, queryExpression);
        return clientCount;
    }

    public int getAllCount() {
        int clientCount = dynamoDBMapper.count(Person.class,new DynamoDBScanExpression());
        return clientCount;
    }

    public Person getPersonByName(String name) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":id", new AttributeValue().withS("dcd7f1c5-ed64-4c99-b10d-f9f92302a54c"));
        eav.put(":name", new AttributeValue().withS(name));
        DynamoDBQueryExpression<Person> queryExpression = new DynamoDBQueryExpression<Person>()
                .withKeyConditionExpression("id = :id and #name = :name")
                .withExpressionAttributeNames(Map.of("#name", "name"))
                .withExpressionAttributeValues(eav);

        List<Person> results = dynamoDBMapper.query(Person.class, queryExpression);
        if (!CollectionUtils.isEmpty(results)) {
            return results.get(0);
        } else {
            return null;
        }
    }
}
