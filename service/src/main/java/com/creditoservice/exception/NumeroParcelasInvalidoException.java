package com.creditoservice.exception;

public class NumeroParcelasInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NumeroParcelasInvalidoException(String mensagem) {
		super(mensagem);
	}

}
