package com.creditoservice.dto;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaxaRegisterDTO(

		@NotBlank(message = "Taxa é obrigatorio") String tipo,

		@NotNull(message = "Taxas são obrigatórias") Map<Integer, Double> taxas,

		boolean isDeleted)

{

}