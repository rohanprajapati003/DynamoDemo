package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.model.dto.PersonDTO;
import com.example.demo.service.PersonService;
import org.apache.http.client.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/person")
public class PersonController {


    @Autowired
    private PersonService personService;


    @GetMapping("/all")
    public ResponseEntity<?> getPerson(){
         return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    @GetMapping(value = "/{personId}")
    public ResponseEntity<?> getPersonById(@PathVariable(value = "personId", required = true) String personId){

        return ResponseEntity.status(HttpStatus.OK).body(personService.getPersonById(personId));

    }

    @GetMapping("/count")
    public ResponseEntity<?> getCountByPersonId(){

        return ResponseEntity.status(HttpStatus.OK).body(personService.getCount());

    }

    @PostMapping("/add")
    public ResponseEntity<?> createPerson(@RequestBody PersonDTO personDTO){

        return ResponseEntity.status(HttpStatus.OK).body(personService.createPerson(personDTO));

    }


    @DeleteMapping("/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "personId", required = true) String personId) {

        personService.deletePersonById(personId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/name/{personName}")
    public ResponseEntity<?> getPersonByName(@PathVariable(value = "personName", required = true) String personName){

        return ResponseEntity.status(HttpStatus.OK).body(personService.getPersonByName(personName));

    }



}
