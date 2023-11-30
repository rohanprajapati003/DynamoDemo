package com.example.demo.repo;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.demo.model.Person;
import com.example.demo.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class PersonRepository  {

    @Autowired
    private Converter converter;
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

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
            return new Person("1","Dummy",21);
        }

    }


    public Person savePerson(Person person) {
        System.out.println("Dynamo");
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
}
