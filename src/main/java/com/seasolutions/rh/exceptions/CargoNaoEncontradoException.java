package com.seasolutions.rh.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CargoNaoEncontradoException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1509779504782792428L;

	public CargoNaoEncontradoException(String message) {
        super(message);
    }
}
