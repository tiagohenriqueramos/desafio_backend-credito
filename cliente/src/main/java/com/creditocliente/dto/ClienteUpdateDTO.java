package com.creditocliente.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteUpdateDTO(
	    @NotBlank(message = "Nome é obrigatorio")
		String nome, 
		
		String celular, 
		
		int score, 
		
		boolean negativado,
		
		boolean idDeleted) {

}