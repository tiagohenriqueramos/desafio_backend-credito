package com.creditoservice.service;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditoservice.domain.Taxa;
import com.creditoservice.dto.ClienteApiResponseDTO;
import com.creditoservice.dto.SimulacaoRequestDTO;
import com.creditoservice.dto.SimulacaoResponseDTO;
import com.creditoservice.exception.CpfInvalidoException;
import com.creditoservice.exception.NumeroParcelasInvalidoException;
import com.creditoservice.exception.TipoTaxaIndeterminadoException;
import com.creditoservice.exception.ValorNecessarioException;
import com.creditoservice.repository.TaxaRepository;

@Service
public class SimulacaoService {

    @Autowired
    private ClienteApiService clienteApiService;

    @Autowired
    private TaxaRepository taxaRepository;

    public SimulacaoResponseDTO simularEmprestimo(SimulacaoRequestDTO request, String cpf) {
        ClienteApiResponseDTO cliente = clienteApiService.getClienteByCpf(cpf);
        if (cliente == null) {
            throw new CpfInvalidoException("CPF inválido ou não encontrado.");
        }

        if (request.getValor() == null || request.getValor() <= 0) {
            throw new ValorNecessarioException("Valor de emprestimo é necessário e deve ser maior que zero.");
        }

        int score = cliente.getScore();
        String nome = cliente.getNome();
        String tipoScore = determinarTipoTaxa(cliente);

        String tipoTaxa = determinarTipoTaxa(cliente);
        double taxa = obterTaxaJuros(tipoTaxa, request.getNumeroParcelas());

        double valorFinal = calcularValorFinal(request.getValor(), taxa, request.getNumeroParcelas());
        double valorParcelas = calcularValorParcelas(valorFinal, request.getNumeroParcelas());

        String valorFinalFormatado = formatarMoeda(valorFinal);
        String valorParcelasFormatado = formatarMoeda(valorParcelas);

        return new SimulacaoResponseDTO(nome, valorParcelasFormatado, request.getNumeroParcelas(), taxa, valorFinalFormatado, score, tipoScore);
    }

    private String determinarTipoTaxa(ClienteApiResponseDTO cliente) {
        if (cliente.isNegativado()) {
            return "NEGATIVADO";
        } else if (cliente.getScore() > 500) {
            return "SCORE_ALTO";
        } else if (cliente.getScore() <= 499) {
            return "SCORE_BAIXO";
        } else {
            throw new TipoTaxaIndeterminadoException("Tipo de taxa não pode ser determinado para o cliente com CPF: " + cliente.getCpf());
        }
    }

    protected  double obterTaxaJuros(String tipoTaxa, int numeroParcelas) {
        Taxa taxaJuros = getTaxaByTipo(tipoTaxa);
        Double taxa = taxaJuros.getTaxas().get(numeroParcelas);
        if (taxa == null) {
            throw new NumeroParcelasInvalidoException("Número de parcelas inválido");
        }
        return taxa;
    }

    private double calcularValorFinal(double valor, double taxa, int numeroParcelas) {
        return valor * Math.pow(1 + taxa, numeroParcelas);
    }

    private double calcularValorParcelas(double valorFinal, int numeroParcelas) {
        return valorFinal / numeroParcelas;
    }

    public Taxa getTaxaByTipo(String tipo) {
        return taxaRepository.findByTipo(tipo)
                .orElseThrow(() -> new RuntimeException("Taxa não encontrada para o tipo: " + tipo));
    }

    private String formatarMoeda(double valor) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return format.format(valor);
    }
}
