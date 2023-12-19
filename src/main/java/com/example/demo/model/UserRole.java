package com.example.demo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class UserRole {

	@DynamoDBHashKey
	@DynamoDBAttribute(attributeName = "tenantId")
	private String			  tenantId;

	@DynamoDBRangeKey
	@DynamoDBAutoGeneratedKey
	@DynamoDBAttribute(attributeName = "id")
	private String			  id;

	@DynamoDBAttribute(attributeName = "name")
	private String name;

	@DynamoDBAttribute
	private Map<String,Access> scRoles = new HashMap<>();

	@Getter
	@Setter
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@DynamoDBDocument
	public static class Access{
		@JsonProperty("view")
		Boolean view;

		@JsonProperty("add")
		Boolean add;

		@JsonProperty("update")
		Boolean update;

		@JsonProperty("delete")
		Boolean delete;

		@JsonProperty("isFullAccess")
		Boolean isFullAccess=false;
	}

}