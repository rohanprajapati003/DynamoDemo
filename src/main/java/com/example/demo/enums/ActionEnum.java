package com.example.demo.enums;

public enum ActionEnum {

	VIEW("view");

	public String getValue() {
		return value;
	}

	String value;

	ActionEnum(String value) {
		this.value = value;
	}



}
