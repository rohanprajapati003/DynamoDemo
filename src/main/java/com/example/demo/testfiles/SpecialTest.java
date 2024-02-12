package com.example.demo.testfiles;

import com.example.demo.model.Person;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpecialTest {

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(null);

		List<Integer> abc = new ArrayList<>();
		abc.add(2);
		abc.add(null);
		abc.add(1);

		System.out.println(CollectionUtils.isEqualCollection(list,abc));

	}

	void check(){
		List<Person> person = new ArrayList<>();

		person.add(new Person("1","Rohan",24));
		person.add(new Person("1","Rohan",25));
		person.add(new Person("2","Varun",26));

		Map<String, Integer> collect = person.stream().collect(Collectors.toMap(Person::getName, Person::getAge, (first,second)-> first));
		System.out.println(collect);

		Person p = new Person();
		p.setPersonList(person);

		System.out.println(p);

		String id = "[1,2]";

		String m =String.format("id IN (%s) AND (status =:status OR partnermap =:=parnternmap)",id);
		System.out.println(m);


	}



}
