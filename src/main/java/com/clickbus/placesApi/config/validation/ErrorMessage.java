package com.clickbus.placesApi.config.validation;

public class ErrorMessage {

	private String message;

	private String field;

	public ErrorMessage(String message, String field) {
		this.message = message;
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return field;
	}

}
