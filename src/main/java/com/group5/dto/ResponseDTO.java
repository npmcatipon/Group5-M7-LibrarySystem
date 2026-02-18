package com.group5.dto;

import com.group5.util.ResponseStatus;

public class ResponseDTO<T> {
	
	private ResponseStatus status;
	private String message;
	private T data;
	
	public ResponseDTO() {}
	
	public ResponseDTO(ResponseStatus status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public ResponseStatus getStatus() {
		return status;
	}
	
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}

}
