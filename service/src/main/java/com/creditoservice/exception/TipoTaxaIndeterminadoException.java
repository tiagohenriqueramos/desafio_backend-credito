package com.creditoservice.exception;

public class TipoTaxaIndeterminadoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TipoTaxaIndeterminadoException(String mensagem) {
		super(mensagem);
	}
}
