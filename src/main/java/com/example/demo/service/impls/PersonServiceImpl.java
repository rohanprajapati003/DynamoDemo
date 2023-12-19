package com.example.demo.service.impls;

import com.example.demo.model.Person;
import com.example.demo.model.dto.PersonDTO;
import com.example.demo.repo.PersonRepository;
import com.example.demo.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class PersonServiceImpl implements PersonService {


    @Autowired
    private PersonRepository                   personRepository;
    @Autowired
    private com.example.demo.utility.converter converter;

    @Override
    public List<Person> findAll() {
        return personRepository.getPersonList();
    }

    @Override
    public Person getPersonById(String id) {
     Person p =personRepository.getPersonById(id);


     if(Objects.nonNull(p)){
         return p;
     }else {
         throw new RuntimeException("Not Exist");
     }
    }

    @Override
    public Person createPerson(PersonDTO personDTO) {
        System.out.println(personDTO);

        if(StringUtils.isEmpty(personDTO.getName())){
            throw new RuntimeException("Name should not be empty");
        }

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

    @Override
    public Integer getCountByPersonId(String personId) {

      return  personRepository.getCountByPersonId(personId);
    }

    @Override
    public Integer getCount() {
            return personRepository.getAllCount();
    }

    @Override
    public Person getPersonByName(String name) {
        return personRepository.getPersonByName(name);
    }


}
