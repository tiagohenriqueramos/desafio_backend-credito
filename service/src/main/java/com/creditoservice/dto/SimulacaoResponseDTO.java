package com.creditoservice.dto;

public class SimulacaoResponseDTO {
	private String nome;
    private String valorParcelas;
    private int numeroParcelas;
    private double taxaJuros;
    private String valorTotal;
    private int score;
    private String tipoScore;

    public SimulacaoResponseDTO() {
    }

	public SimulacaoResponseDTO(String nome, String valorParcelas, int numeroParcelas, double taxaJuros,
			String valorTotal, int score, String tipoScore) {
		super();
		this.nome = nome;
		this.valorParcelas = valorParcelas;
		this.numeroParcelas = numeroParcelas;
		this.taxaJuros = taxaJuros;
		this.valorTotal = valorTotal;
		this.score = score;
		this.tipoScore = tipoScore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValorParcelas() {
		return valorParcelas;
	}

	public void setValorParcelas(String valorParcelas) {
		this.valorParcelas = valorParcelas;
	}

	public int getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(int numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public double getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(double taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getTipoScore() {
		return tipoScore;
	}

	public void setTipoScore(String tipoScore) {
		this.tipoScore = tipoScore;
	}
}