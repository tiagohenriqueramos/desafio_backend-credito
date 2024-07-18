package com.creditoservice.exception;

public class ValorNecessarioException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ValorNecessarioException(String mensagem) {
		super(mensagem);
	}

}
