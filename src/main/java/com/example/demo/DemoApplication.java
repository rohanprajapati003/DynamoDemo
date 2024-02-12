package com.example.demo;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.example.demo.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);
		System.out.println(Person.Status.New.name());


	}

}
