package com.boss.storehelmets.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class MyFileNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MyFileNotFoundException(String message) {
		super(message);
	}

	public MyFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
