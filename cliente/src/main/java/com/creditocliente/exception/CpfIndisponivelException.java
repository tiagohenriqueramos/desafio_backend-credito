package com.creditocliente.exception;

public class CpfIndisponivelException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CpfIndisponivelException(String mensagem) {
		super(mensagem);
	}

}