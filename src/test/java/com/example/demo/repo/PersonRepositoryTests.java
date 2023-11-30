package com.example.demo.repo;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.demo.AbstractIntegrationTest;
import com.example.demo.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTests extends AbstractIntegrationTest {

    @Autowired
    PersonRepository personRepository;

    Person mockPerson;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockPerson = new Person("p1", "Rohan", 20);
        personRepository.savePerson(mockPerson);
    }




    @AfterEach
    public void tearDown(){

        mockPerson=null;
        personRepository.deletePersonById(mockPerson.getId());

    }


  void savePerson(){

  }

}
