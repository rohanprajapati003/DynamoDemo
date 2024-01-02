package com.example.demo.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.demo.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserRepo {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;


	public UserRole add(UserRole userRole){
		dynamoDBMapper.save(userRole);
		return userRole;
	}

	public UserRole getUserRoleByTenantId(String id) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":tenantId", new AttributeValue().withS(id));
		DynamoDBQueryExpression<UserRole> queryExpression = new DynamoDBQueryExpression<UserRole>()
			.withKeyConditionExpression("tenantId = :tenantId")
			.withExpressionAttributeValues(eav);
		List<UserRole> results = dynamoDBMapper.query(UserRole.class, queryExpression);
		if (!CollectionUtils.isEmpty(results)) {
			return results.get(0);
		} else {
			return null;
		}
	}

	public UserRole getUserRoleByName(String name) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":name", new AttributeValue().withS(name));
		eav.put(":tenantId", new AttributeValue().withS("481a0cbd-e503-4939-ac49-51311d437e98"));
		DynamoDBQueryExpression<UserRole> queryExpression = new DynamoDBQueryExpression<UserRole>()
			.withKeyConditionExpression("tenantId = :tenantId")
			.withFilterExpression("#name = :name")
			.withExpressionAttributeNames(Map.of("#name", "name"))
			.withExpressionAttributeValues(eav);
		List<UserRole> results = dynamoDBMapper.query(UserRole.class, queryExpression);
		if (!CollectionUtils.isEmpty(results)) {
			return results.get(0);
		} else {
			return null;
		}
	}

	public List<UserRole> fetchUserRoleList() {
		DynamoDBScanExpression queryExpression = new DynamoDBScanExpression();

		List<UserRole> userRoleList = dynamoDBMapper.scan(UserRole.class, queryExpression);
		return userRoleList;
	}


}
