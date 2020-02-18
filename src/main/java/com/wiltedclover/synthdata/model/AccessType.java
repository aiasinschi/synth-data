package com.wiltedclover.synthdata.model;

public enum AccessType {

	PUBLIC("PUB", "Public"),
	PRIVATE("PRV", "Private");

	private String name;

	private String code;

	AccessType(String code, String name) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
}
