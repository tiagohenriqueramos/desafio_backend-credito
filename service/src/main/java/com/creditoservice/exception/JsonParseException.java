package com.creditoservice.exception;

public class JsonParseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public JsonParseException(String mensagem) {
		super(mensagem);
	}

}
