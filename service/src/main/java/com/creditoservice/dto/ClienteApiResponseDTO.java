package com.creditoservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteApiResponseDTO {
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("celular")
    private String celular;

    @JsonProperty("score")
    private int score;

    @JsonProperty("negativado")
    private boolean negativado;

    public ClienteApiResponseDTO() {
    }

    public ClienteApiResponseDTO(String nome, String cpf, String celular, int score, boolean negativado) {
        this.nome = nome;
        this.cpf = cpf;
        this.celular = celular;
        this.score = score;
        this.negativado = negativado;
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

    @Override
    public String toString() {
        return "ClienteApiResponseDTO{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", celular='" + celular + '\'' +
                ", score=" + score +
                ", negativado=" + negativado +
                '}';
    }
}
