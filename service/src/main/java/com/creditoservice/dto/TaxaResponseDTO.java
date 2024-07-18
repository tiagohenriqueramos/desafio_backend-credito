package com.creditoservice.dto;

import java.util.Map;

import com.creditoservice.domain.Taxa;

public class TaxaResponseDTO {

	private String tipo;
	private Map<Integer, Double> taxas;

	public TaxaResponseDTO() {
	}

	public TaxaResponseDTO(Taxa taxa) {
		super();
		this.tipo = taxa.getTipo();
		this.taxas = taxa.getTaxas();
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Map<Integer, Double> getTaxas() {
		return taxas;
	}

	public void setTaxas(Map<Integer, Double> taxas) {
		this.taxas = taxas;
	}

}