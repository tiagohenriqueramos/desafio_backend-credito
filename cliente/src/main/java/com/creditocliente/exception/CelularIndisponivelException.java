package com.creditocliente.exception;

public class CelularIndisponivelException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CelularIndisponivelException(String mensagem) {
		super(mensagem);
	}

}