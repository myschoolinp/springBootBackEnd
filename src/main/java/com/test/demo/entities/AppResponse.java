package com.test.demo.entities;

import java.util.List;

public class AppResponse<E> {
	
	private List<E> data;
	private String status;
	private String message;
	public List<E> getData() {
		return data;
	}
	public void setData(List<E> data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AppResponse(List<E> data, String status, String message) {
		super();
		this.data = data;
		this.status = status;
		this.message = message;
	}
	public AppResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
