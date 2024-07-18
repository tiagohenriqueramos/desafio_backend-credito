package com.creditoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditoservice.dto.SimulacaoRequestDTO;
import com.creditoservice.dto.SimulacaoResponseDTO;
import com.creditoservice.exception.ApiResponse;
import com.creditoservice.service.SimulacaoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/simulacao")
public class SimuladorController {
	
	@Autowired
	private SimulacaoService simulacaoService;
	
	@PostMapping("/{cpf}")
	public ResponseEntity<ApiResponse<SimulacaoResponseDTO>> simularEmprestimo(@PathVariable String cpf,
			@RequestBody SimulacaoRequestDTO request) {
		SimulacaoResponseDTO response = simulacaoService.simularEmprestimo(request, cpf);
				
		return ResponseEntity.ok()
				.body(new ApiResponse<>(HttpStatus.OK.value(), "Simulação realizada com sucesso!", response));
	}
	
	
	
}
