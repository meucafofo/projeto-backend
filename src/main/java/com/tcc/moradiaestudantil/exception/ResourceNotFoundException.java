package com.tcc.moradiaestudantil.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -887508461595906264L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
