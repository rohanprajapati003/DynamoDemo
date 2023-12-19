package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.model.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    Person getPersonById(String id);

    Person createPerson(PersonDTO personDTO);


    void deletePersonById(String personId);

    Integer getCountByPersonId(String personId);

    Integer getCount();

    Person getPersonByName(String name);
}
