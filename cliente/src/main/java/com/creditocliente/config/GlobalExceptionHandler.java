package com.creditocliente.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.creditocliente.exception.ApiResponse;
import com.creditocliente.exception.CelularIndisponivelException;
import com.creditocliente.exception.CpfIndisponivelException;
import com.creditocliente.exception.InvalidParameterException;
import com.creditocliente.exception.NotFoundException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> handleValidationException(NotFoundException ex) {
		ApiResponse<?> errorResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InvalidParameterException.class)
	public ResponseEntity<Object> handleValidationException(InvalidParameterException ex) {
		ApiResponse<?> errorResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CpfIndisponivelException.class)
	public ResponseEntity<Object> handleValidationException(CpfIndisponivelException ex) {
		ApiResponse<?> errorResponse = new ApiResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = CelularIndisponivelException.class)
	public ResponseEntity<Object> handleValidationException(CelularIndisponivelException ex) {
		ApiResponse<?> errorResponse = new ApiResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors();
	    return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }
	
	private record DadosErroValidacao(String campo, String mensagem) {
		public DadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
	  @ExceptionHandler(UnrecognizedPropertyException.class)
	    public ResponseEntity<String> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo não reconhecido: " + ex.getPropertyName());
	    }
}