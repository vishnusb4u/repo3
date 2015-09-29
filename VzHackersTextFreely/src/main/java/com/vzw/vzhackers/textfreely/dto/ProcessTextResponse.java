package com.vzw.vzhackers.textfreely.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "processTextResponse")
public class ProcessTextResponse {

	private String responseMessage;
	private String operation;

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

}
