package com.seasolutions.rh.exceptions;

public class SetorNaoEncontradoException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8938685220354699533L;

	public SetorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
