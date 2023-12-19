package com.example.demo.model.dto;

import com.example.demo.model.BankList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankDTO {

	@JsonProperty("id")
	private String		 id;

	@JsonProperty("tenantId")
	private String		 tenantId;

	@JsonProperty("banks")
	private List<BankList> banks = new ArrayList<>();

	@JsonProperty("name")
	private String		 name;

}
