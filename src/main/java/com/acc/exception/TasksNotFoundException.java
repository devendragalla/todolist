package com.acc.exception;

public class TasksNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public TasksNotFoundException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
