package com.creditoservice.config;

import java.security.InvalidParameterException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.creditoservice.exception.ApiResponse;
import com.creditoservice.exception.CpfInvalidoException;
import com.creditoservice.exception.NotFoundException;
import com.creditoservice.exception.NumeroParcelasInvalidoException;
import com.creditoservice.exception.TipoTaxaIndeterminadoException;
import com.creditoservice.exception.ValorNecessarioException;

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

	@ExceptionHandler(value = NumeroParcelasInvalidoException.class)
	public ResponseEntity<Object> handleNumeroParcelasInvalidoException(NumeroParcelasInvalidoException ex) {
		ApiResponse<?> errorResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ValorNecessarioException.class)
	public ResponseEntity<Object> handleValorNecessarioException(ValorNecessarioException ex) {
		ApiResponse<?> errorResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = TipoTaxaIndeterminadoException.class)
	public ResponseEntity<Object> handleTipoTaxaIndeterminadoException(TipoTaxaIndeterminadoException ex) {
		ApiResponse<?> errorResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CpfInvalidoException.class)
	public ResponseEntity<Object> handleCpfInvalidoException(CpfInvalidoException ex) {
		ApiResponse<?> errorResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		ApiResponse<?> errorResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),
				"Erro de leitura do JSON: " + ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = NoHandlerFoundException.class)
	public ResponseEntity<Object> handleRotaNaoEncontradaException(NoHandlerFoundException ex) {
		ApiResponse<?> errorResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Rota não encontrada.");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
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
}