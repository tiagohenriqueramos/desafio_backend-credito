package com.creditocliente.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteRegisterDTO(
	    @NotBlank(message = "Nome é obrigatorio")
		String nome, 
		
		@NotBlank(message = "CPF é obrigatorio")
		String cpf, 
		
	    @NotBlank(message = "Celular é obrigatório")
		String celular, 
		
		int score, 
		
		boolean negativado,
		
		boolean idDeleted) {

}