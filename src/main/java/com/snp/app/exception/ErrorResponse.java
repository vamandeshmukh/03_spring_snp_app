package com.snp.app.exception;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorResponse {
	public ErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	// General error message about nature of error
	private String message;

	// Specific errors in API request processing
	private int status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}