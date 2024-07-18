package com.creditoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditoservice.dto.TaxaRegisterDTO;
import com.creditoservice.dto.TaxaResponseDTO;
import com.creditoservice.exception.ApiResponse;
import com.creditoservice.service.TaxaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/taxa")
public class TaxaController {

	@Autowired
	private final TaxaService taxaService;

	

	public TaxaController(TaxaService taxaService) {
		this.taxaService = taxaService;
	}

	@GetMapping("/listar")
	public ResponseEntity<ApiResponse<Page<TaxaResponseDTO>>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		Page<TaxaResponseDTO> taxasPage = taxaService.listarClientes(paginacao);
		ApiResponse<Page<TaxaResponseDTO>> response = new ApiResponse<>(HttpStatus.OK.value(),
				"Listagem concluída com sucesso!", taxasPage);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<ApiResponse<TaxaResponseDTO>> buscar(@PathVariable @Valid String id) {
		TaxaResponseDTO fornecedor = taxaService.buscarTaxa(id);
		return ResponseEntity.ok()
				.body(new ApiResponse<>(HttpStatus.OK.value(), "Busca realizada com sucesso!", fornecedor));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<ApiResponse<TaxaRegisterDTO>> cadastrar(@RequestBody @Valid TaxaRegisterDTO novaTaxa) {
		taxaService.cadastrar(novaTaxa);
		return ResponseEntity.ok()
				.body(new ApiResponse<>(HttpStatus.OK.value(), "Taxa cadastrada com sucesso!", novaTaxa));
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<ApiResponse<TaxaRegisterDTO>> alterarDados(@PathVariable @Valid String id,
			@RequestBody @Valid TaxaRegisterDTO taxaAtualizado) {
		taxaService.alterarDados(id, taxaAtualizado);
		return ResponseEntity.ok()
				.body(new ApiResponse<>(HttpStatus.OK.value(), "Dados alterados com sucesso!", taxaAtualizado));
	}

	

	@DeleteMapping("/delete/{taxa}")
	public ResponseEntity<ApiResponse<?>> deletar(@PathVariable @Valid String taxa) {
		taxaService.deletar(taxa);
		return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Taxa deletada com sucesso!"));
	}
}
