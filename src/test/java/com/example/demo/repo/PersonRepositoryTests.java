package com.example.demo.repo;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.demo.config.DynamoDBConfig;
import com.example.demo.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

//@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, DynamoDBConfig.class})
public class PersonRepositoryTests {

/*
    @Autowired
    private  PersonRepository personRepository;

    @Mock
    private DynamoDBMapper dynamoDBMapper;


    Person mockPerson;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        mockPerson = new Person("p1", "Rohan", 20);
        personRepository.savePerson(mockPerson);

    }

    @Test
    void testCount(){

        Mockito.when(personRepository.getAllCount()).thenReturn(1);

        int allCount = personRepository.getAllCount();

        Assertions.assertEquals(1,allCount);

    }

    @AfterEach
    public void tearDown(){

        mockPerson=null;
        personRepository.deletePersonById(mockPerson.getId());

    }

  void savePerson(){

  }
*/

}
