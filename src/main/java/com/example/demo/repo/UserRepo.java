package com.example.demo.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepo {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;


}
