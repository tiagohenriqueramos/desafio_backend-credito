package com.creditoservice.domain;

import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.creditoservice.dto.TaxaRegisterDTO;

@Document
public class Taxa {

	@Id
	private String id;
	private String tipo;
	private Map<Integer, Double> taxas;
	private boolean isDeleted = false;

	public Taxa() {
	}

	public Taxa(String id, String tipo, Map<Integer, Double> taxas, boolean isDeleted) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.taxas = taxas;
		this.isDeleted = isDeleted;
	}

	public Taxa(TaxaRegisterDTO novaTaxa) {
		this.tipo = novaTaxa.tipo();
		this.taxas = novaTaxa.taxas();
		this.isDeleted = novaTaxa.isDeleted();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Taxa other = (Taxa) obj;
		return Objects.equals(id, other.id);
	}

}