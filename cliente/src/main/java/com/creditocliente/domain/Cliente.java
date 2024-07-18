package com.creditocliente.domain;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.creditocliente.dto.ClienteRegisterDTO;

@Document
public class Cliente {

	private static final int SCORE_DEFAULT = 0;

	@Id
	private String id;
	private String nome;
	@Indexed(unique = true)
	private String cpf;
	private String celular;
	private int score = SCORE_DEFAULT;
	private boolean negativado;
	private boolean isDeleted = false;

	public Cliente() {
	}

	public Cliente(String nome, String cpf, String celular, boolean negativado, boolean isDeleted) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.celular = celular;
		this.negativado = negativado;
		this.isDeleted = isDeleted;
	}

	public Cliente(ClienteRegisterDTO novoCliente) {
		this.nome = novoCliente.nome();
		this.cpf = novoCliente.cpf();
		this.celular = novoCliente.celular();
		this.negativado = novoCliente.negativado();
		this.isDeleted = novoCliente.idDeleted();
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}

}