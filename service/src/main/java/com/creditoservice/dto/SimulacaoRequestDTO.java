package com.creditoservice.dto;

import jakarta.validation.constraints.NotBlank;

public class SimulacaoRequestDTO {
	
	@NotBlank(message = "Numero de parcelas é obrigatiorio")
	private int numeroParcelas;
	@NotBlank(message = "Valor é obrigatorio")
	private Double valor;

	public SimulacaoRequestDTO() {
	}

	public SimulacaoRequestDTO(
			@NotBlank(message = "Numero de parcelas é obrigatiorio") int numeroParcelas,
			@NotBlank(message = "Valor é obrigatorio") Double valor) {
		super();
	
		this.numeroParcelas = numeroParcelas;
		this.valor = valor;
	}

	

	public int getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(int numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
