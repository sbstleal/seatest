package com.seasolutions.rh.exceptions;

public class CargoDuplicadoException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6450404769966318827L;

	public CargoDuplicadoException(String mensagem) {
        super(mensagem);
    }
}
