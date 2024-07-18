package com.creditocliente.dto;

import com.creditocliente.domain.Cliente;

public class ClienteResponseDTO {

	private String nome;
	private String cpf;
	private String celular;
	private int score;
	private boolean negativado;

	public ClienteResponseDTO() {
	}

	public ClienteResponseDTO(Cliente cliente) {
		super();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.celular = cliente.getCelular();
		this.score = cliente.getScore();
		this.negativado = cliente.isNegativado();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isNegativado() {
		return negativado;
	}

	public void setNegativado(boolean negativado) {
		this.negativado = negativado;
	}

}