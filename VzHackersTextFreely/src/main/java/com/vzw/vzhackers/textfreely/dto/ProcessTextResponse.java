package com.vzw.vzhackers.textfreely.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "processTextResponse")
public class ProcessTextResponse {

	private String responseMessage;
	private String operation;
	private String statusCode;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
