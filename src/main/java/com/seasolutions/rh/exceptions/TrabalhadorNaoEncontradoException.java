package com.seasolutions.rh.exceptions;

public class TrabalhadorNaoEncontradoException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3002286986407455742L;

	public TrabalhadorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}