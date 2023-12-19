package com.example.demo.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.demo.model.Bank;
import com.example.demo.model.BankList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankRepo {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	public Bank getBankByTenantId(String id) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":tenantId", new AttributeValue().withS(id));
		DynamoDBQueryExpression<Bank> queryExpression = new DynamoDBQueryExpression<Bank>()
			.withKeyConditionExpression("tenantId = :tenantId")
			.withExpressionAttributeValues(eav);
		List<Bank> results = dynamoDBMapper.query(Bank.class, queryExpression);
		if (!CollectionUtils.isEmpty(results)) {
			return results.get(0);
		} else {
			return null;
		}
	}

	public Bank getBankById(String id) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":tenantId", new AttributeValue().withS("abc"));
		eav.put(":id", new AttributeValue().withS(id));
		DynamoDBQueryExpression<Bank> queryExpression = new DynamoDBQueryExpression<Bank>()
			.withKeyConditionExpression("tenantId = :tenantId and id = :id")
			.withExpressionAttributeValues(eav);
		List<Bank> results = dynamoDBMapper.query(Bank.class, queryExpression);
		if (!CollectionUtils.isEmpty(results)) {
			return results.get(0);
		} else {
			return null;
		}
	}

	public Bank saveBank(Bank Bank) {
		dynamoDBMapper.save(Bank);
		return Bank;
	}

	public int getCountByTenantId(String id) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":tenantId", new AttributeValue().withS(id));
		DynamoDBQueryExpression<Bank> queryExpression = new DynamoDBQueryExpression<Bank>()
			.withKeyConditionExpression("tenantId = :tenantId")
			.withExpressionAttributeValues(eav);
		List<Bank> results = dynamoDBMapper.query(Bank.class, queryExpression);
		if (!CollectionUtils.isEmpty(results)) {
			return results.get(0).getBanks().size();
		} else {
			return 0;
		}
	}

	public List<BankList> fetchBankList(String tenantId) {

		Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
		expressionAttributeValues.put(":tenantId", new AttributeValue().withS(tenantId));
		DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()
			.withFilterExpression("attribute_not_exists(tenantId) OR tenantId = :tenantId")
			.withExpressionAttributeValues(expressionAttributeValues);

		List<BankList> industryList = dynamoDBMapper.scan(BankList.class, queryExpression);
		if (!CollectionUtils.isEmpty(industryList)) {
			return industryList;
		}
		return null;
	}

	public BankList saveBankList(BankList bankList) {
		dynamoDBMapper.save(bankList);
		return bankList;
	}

}
