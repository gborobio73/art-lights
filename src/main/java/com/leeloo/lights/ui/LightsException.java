package com.leeloo.lights.ui;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
//@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Fixed message here")
@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class LightsException extends RuntimeException{
	public LightsException(String message) {
		super(message);
	}
}
