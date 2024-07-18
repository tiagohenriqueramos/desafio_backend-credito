package com.creditoservice.exception;

public class CpfInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CpfInvalidoException(String mensagem) {
		super(mensagem);
	}
}
