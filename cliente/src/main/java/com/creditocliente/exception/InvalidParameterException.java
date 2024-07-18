package com.creditocliente.exception;

public class InvalidParameterException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidParameterException(String mensagem) {
		super(mensagem);
	}
}