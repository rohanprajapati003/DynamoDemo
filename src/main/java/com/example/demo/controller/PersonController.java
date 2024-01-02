package com.example.demo.controller;

import com.example.demo.model.dto.PersonDTO;
import com.example.demo.security.CustomAnnotation;
import com.example.demo.service.PersonService;
import com.example.demo.utility.AuthorizationCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {


    @Autowired
    private PersonService personService;

    @Autowired
    private AuthorizationCheck authorizationCheck;


    @CustomAnnotation(debug = true)
    @GetMapping("/all")
    public ResponseEntity<?> getPerson(){
        ;
         return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    @GetMapping("/{personId}")
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
