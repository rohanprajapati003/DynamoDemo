package com.example.demo.service.impls;

import com.example.demo.model.Person;
import com.example.demo.model.dto.PersonDTO;
import com.example.demo.repo.PersonRepository;
import com.example.demo.service.PersonService;
import com.example.demo.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class PersonServiceImpl implements PersonService {


    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private Converter converter;

    @Override
    public List<Person> findAll() {
        return personRepository.getPersonList();
    }

    @Override
    public Person getPersonById(String id) {
     return personRepository.getPersonById(id);
    }

    @Override
    public Person createPerson(PersonDTO personDTO) {
        System.out.println(personDTO);
        /*Person person = new Person();
        person.setId(personDTO.getId());
        person.setName(personDTO.getName());
        person.setAge(personDTO.getAge());*/
        Person person = converter.convertDtoToModel(personDTO, Person.class);
       // System.out.println(person);
        Person person1 = personRepository.savePerson(person);
        return person1;
    }

    @Override
    public void deletePersonById(String personId) {

        Person personById = personRepository.getPersonById(personId);
        if(personById==null){
            throw new RuntimeException("Not Found");
        }

        personRepository.deletePersonById(personId);
    }
}
