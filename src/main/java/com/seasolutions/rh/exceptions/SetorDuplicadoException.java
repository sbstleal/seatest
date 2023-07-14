package com.seasolutions.rh.exceptions;

public class SetorDuplicadoException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1012797903241826472L;

	public SetorDuplicadoException(String mensagem) {
        super(mensagem);
    }
}

