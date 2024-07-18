package com.creditoservice.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.creditoservice.domain.Taxa;
import com.creditoservice.dto.TaxaRegisterDTO;
import com.creditoservice.dto.TaxaResponseDTO;
import com.creditoservice.exception.NotFoundException;
import com.creditoservice.repository.TaxaRepository;

import jakarta.validation.Valid;

@Service
public class TaxaService {

	@Autowired
	private  TaxaRepository taxaRepository;

	public TaxaService(TaxaRepository taxaRepository) {
		this.taxaRepository = taxaRepository;
	}

	 
	public TaxaResponseDTO buscarTaxa(@Valid String id) {
		Taxa taxa = taxaRepository.findById(id).orElseThrow(() -> new NotFoundException("Taxa não encontrado"));

		return new TaxaResponseDTO(taxa);
	}
	
	public Page<TaxaResponseDTO> listarClientes(Pageable paginacao) {
		Page<Taxa> taxaPage = taxaRepository.findAll(paginacao);

		List<TaxaResponseDTO> listaClientesDTO = taxaPage.getContent().stream().map(TaxaResponseDTO::new)
				.collect(Collectors.toList());

		return new PageImpl<>(listaClientesDTO, paginacao, taxaPage.getTotalElements());
	}
	public void cadastrar(@Valid TaxaRegisterDTO novaTaxa) {
		if (taxaRepository.findByTipo(novaTaxa.tipo()).isPresent()) {
			throw new NotFoundException("Taxa já cadastrada!");
		}
		Taxa taxa = new Taxa(novaTaxa);
		taxaRepository.save(taxa);
	}

	public void alterarDados(@Valid String id, @Valid TaxaRegisterDTO dadosAtualizado) {
		Taxa taxa = taxaRepository.findById(id).orElseThrow(() -> new NotFoundException("Taxa não encontrado"));
						
		if(dadosAtualizado.tipo() != null && !Objects.equals(taxa.getTipo(), dadosAtualizado.tipo())) {
			taxa.setTipo(dadosAtualizado.tipo());
		}
		
		if(dadosAtualizado.taxas() != null && !Objects.equals(taxa.getTaxas(), dadosAtualizado.taxas())) {
			taxa.setTaxas(dadosAtualizado.taxas());
		}
		
		taxaRepository.save(taxa);
	}
	
	public void deletar(@Valid String tipo) {
		Taxa taxa = taxaRepository.findByTipo(tipo).orElseThrow(() -> new NotFoundException("Taxa não encontrado"));
		taxa.setDeleted(true);
		taxaRepository.save(taxa);
	}
	
	
}