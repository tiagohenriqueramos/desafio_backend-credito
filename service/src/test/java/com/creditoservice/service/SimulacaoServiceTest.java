package com.creditoservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.creditoservice.domain.Taxa;
import com.creditoservice.dto.ClienteApiResponseDTO;
import com.creditoservice.dto.SimulacaoRequestDTO;
import com.creditoservice.dto.SimulacaoResponseDTO;
import com.creditoservice.exception.CpfInvalidoException;
import com.creditoservice.exception.NumeroParcelasInvalidoException;
import com.creditoservice.exception.ValorNecessarioException;
import com.creditoservice.repository.TaxaRepository;

public class SimulacaoServiceTest {

    @Mock
    private TaxaRepository taxaRepository;

    @Mock
    private ClienteApiService clienteApiService;

    @InjectMocks
    private SimulacaoService simulacaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Map<Integer, Double> taxasNegativado = new HashMap<>();
        taxasNegativado.put(6, 0.04);
        taxasNegativado.put(12, 0.045);
        taxasNegativado.put(36, 0.05);

        Taxa taxaNegativado = new Taxa();
        taxaNegativado.setTipo("NEGATIVADO");
        taxaNegativado.setTaxas(taxasNegativado);

        Map<Integer, Double> taxasAlto = new HashMap<>();
        taxasAlto.put(6, 0.02);
        taxasAlto.put(12, 0.025);
        taxasAlto.put(18, 0.03);

        Taxa taxaAlto = new Taxa();
        taxaAlto.setTipo("SCORE_ALTO");
        taxaAlto.setTaxas(taxasAlto);

        Map<Integer, Double> taxasBaixo = new HashMap<>();
        taxasBaixo.put(36, 0.05);
        taxasBaixo.put(12, 0.06);
        taxasBaixo.put(18, 0.07);

        Taxa taxaBaixo = new Taxa();
        taxaBaixo.setTipo("SCORE_BAIXO");
        taxaBaixo.setTaxas(taxasBaixo);

        when(taxaRepository.findByTipo("NEGATIVADO")).thenReturn(Optional.of(taxaNegativado));
        when(taxaRepository.findByTipo("SCORE_ALTO")).thenReturn(Optional.of(taxaAlto));
        when(taxaRepository.findByTipo("SCORE_BAIXO")).thenReturn(Optional.of(taxaBaixo));

        ClienteApiResponseDTO cliente = new ClienteApiResponseDTO();
        cliente.setCpf("12345678901");
        cliente.setNome("Teste");
        cliente.setScore(0);
        cliente.setNegativado(false);

        when(clienteApiService.getClienteByCpf(anyString())).thenReturn(cliente);
    }

    @Test
    @DisplayName("TESTE OBTER TAXA DE JUROS VALIDA")
    public void testObterTaxaJurosValida() {
        double taxa = simulacaoService.obterTaxaJuros("NEGATIVADO", 6);
        assertEquals(0.04, taxa);
    }

    @Test
    public void testNumeroParcelasInvalido() {
        assertThrows(NumeroParcelasInvalidoException.class, () -> {
            simulacaoService.obterTaxaJuros("SCORE_ALTO", 0);
        });
    }

    @Test
    public void testGetTaxaByTipo() {
        Taxa taxa = simulacaoService.getTaxaByTipo("SCORE_ALTO");
        assertNotNull(taxa);
        assertEquals("SCORE_ALTO", taxa.getTipo());
    }

    @Test
    public void testGetTaxaByTipoInvalido() {
        assertThrows(RuntimeException.class, () -> {
            simulacaoService.getTaxaByTipo("SCORE_INVALIDO");
        });
    }

    @Test
    public void testSimularEmprestimoCpfInvalido() {
        when(clienteApiService.getClienteByCpf(anyString())).thenReturn(null);

        SimulacaoRequestDTO request = new SimulacaoRequestDTO();
        request.setValor(10000.0);
        request.setNumeroParcelas(12);

        assertThrows(CpfInvalidoException.class, () -> {
            simulacaoService.simularEmprestimo(request, "00000000000");
        });
    }

    @Test
    public void testSimularEmprestimoValorNecessarioException() {
        SimulacaoRequestDTO request = new SimulacaoRequestDTO();
        request.setValor(0.0);
        request.setNumeroParcelas(12);

        assertThrows(ValorNecessarioException.class, () -> {
            simulacaoService.simularEmprestimo(request, "12345678901");
        });
    }

    @Test
    public void testSimularEmprestimoValido() {
        SimulacaoRequestDTO request = new SimulacaoRequestDTO();
        request.setValor(10000.0);
        request.setNumeroParcelas(36);

        SimulacaoResponseDTO response = simulacaoService.simularEmprestimo(request, "12345678901");

        assertNotNull(response);
        assertEquals("Teste", response.getNome());

        String valorParcelasEsperado = formatarMoeda(10000.0 * Math.pow(1 + 0.05, 36) / 36);
        String valorTotalEsperado = formatarMoeda(10000.0 * Math.pow(1 + 0.05, 36));

        assertEquals(valorParcelasEsperado, response.getValorParcelas());
        assertEquals(36, response.getNumeroParcelas());
        assertEquals(0.05, response.getTaxaJuros());
        assertEquals(valorTotalEsperado, response.getValorTotal());
        assertEquals(0, response.getScore());
        assertEquals("SCORE_BAIXO", response.getTipoScore());
    }

    private String formatarMoeda(double valor) {
        java.text.NumberFormat format = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("pt", "BR"));
        return format.format(valor);
    }
}