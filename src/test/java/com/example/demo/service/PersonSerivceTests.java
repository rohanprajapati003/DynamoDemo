package com.example.demo.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.demo.model.Person;
import com.example.demo.model.dto.PersonDTO;
import com.example.demo.repo.PersonRepository;
import com.example.demo.service.impls.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonSerivceTests {


    @InjectMocks
    PersonServiceImpl personService;

    @Mock
    PersonRepository personRepository;

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private com.example.demo.utility.converter converter;

    Person mockPerson;


    @BeforeEach
    public void init() {
         MockitoAnnotations.openMocks(this);
    }


    @BeforeEach
    void setup(){
      /*  Person p = new Person("abc","Rohan",23);

        Mockito.when(personRepository.getPersonById("abc")).thenReturn(p);*/
        mockPerson = new Person("Abc","Rohan",23);

    }


    @Test
    void testGetPersonList(){

        List<Person> list = new ArrayList<>();

        Person p = new Person("Abc","Rohan",24);
        Person p1 = new Person("Bcd","Rohan",25);
        Person p2 = new Person("pqr","Rohan",24);
        Person p3= new Person("xyz","Rohan",24);

        list.add(p);list.add(p1);list.add(p2);list.add(p3);
        Mockito.when(personRepository.getPersonList()).thenReturn(list);
        List<Person> people = personService.findAll();

        assertEquals(4,people.size());

    }

    @Test
    void testDelete_NotFound(){

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
                    personService.deletePersonById("xyz");
        });
        assertEquals("Not Found",runtimeException.getMessage());
    }



    @Test
    void testCreatePerson(){

        PersonDTO personDTO = new PersonDTO("abc","Rohan",22);
        Person expectedPerson = new Person("abc","Rohan",22);

        Mockito.when(converter.convertDtoToModel(personDTO,Person.class)).thenReturn(expectedPerson);
        Mockito.when(personRepository.savePerson(Mockito.any())).thenReturn(expectedPerson);

        Person person = personService.createPerson(personDTO);

        assertEquals(expectedPerson,person);

    }

    @Test
    void testGetPersonById(){

        String personID = "abc";
        Person expectedPerson = new Person("abc","Rohan",22);
        Mockito.when(personRepository.getPersonById(personID)).thenReturn(expectedPerson);
        Person personById = personService.getPersonById(personID);
        assertEquals(personID,personById.getId());

    }
    @Test
    void testNotFoundPersonById(){

        String nonExistingPersonId = "100";

        // Mock PersonRepository behavior for a non-existing person
        Mockito.when(personRepository.getPersonById(nonExistingPersonId)).thenReturn(null);

        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            personService.getPersonById(nonExistingPersonId);
        });

        // Verify that getPersonById was called with the expected argument
        Mockito.verify(personRepository).getPersonById(nonExistingPersonId);

        // Verify the exception message
        assertEquals("Not Exist", exception.getMessage());

    }


    @Test
    void testNameShouldNotbeEmpty(){
        PersonDTO personDTO = new PersonDTO("abc","",22);

        Exception exception = assertThrows(RuntimeException.class, () ->
                personService.createPerson(personDTO));
        assertEquals("Name should not be empty", exception.getMessage());


    }

    @Test
    void testDeletePerson(){

        // Arrange
        String personId = "1";

        // Mock PersonRepository behavior
        Mockito.when(personRepository.getPersonById(personId))
                .thenReturn(new Person("2", "TestPerson", 25));

        //whose Id is 2 that person will be deleted
        // Act
        personService.deletePersonById(personId);

        // Verify that getPersonById was called with the expected argument
        Mockito.verify(personRepository).getPersonById(personId);

        // Verify that deletePersonById was called with the expected argument
        Mockito.verify(personRepository).deletePersonById(personId);
    }

    @Test
    void deleteThrowExcpetion(){

        String personId = "1";

        // Mock PersonRepository behavior
        Mockito.when(personRepository.getPersonById(personId))
                .thenReturn(null);
        Exception exception = assertThrows(RuntimeException.class, () ->
                personService.deletePersonById(personId));
        assertEquals("Not Found", exception.getMessage());

    }





}
