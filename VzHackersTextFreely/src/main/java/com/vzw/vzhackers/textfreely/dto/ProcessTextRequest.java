package com.vzw.vzhackers.textfreely.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "processTextRequest")
public class ProcessTextRequest {

	private String text;
	private String corrId;
	private String mtn;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCorrId() {
		return corrId;
	}

	public void setCorrId(String corrId) {
		this.corrId = corrId;
	}

	public String getMtn() {
		return mtn;
	}

	public void setMtn(String mtn) {
		this.mtn = mtn;
	}

}
