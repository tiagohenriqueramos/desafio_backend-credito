package com.creditocliente.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.creditocliente.domain.Cliente;
import com.creditocliente.dto.ClienteRegisterDTO;
import com.creditocliente.dto.ClienteResponseDTO;
import com.creditocliente.dto.ClienteUpdateDTO;
import com.creditocliente.exception.CelularIndisponivelException;
import com.creditocliente.exception.CpfIndisponivelException;
import com.creditocliente.exception.NotFoundException;
import com.creditocliente.repository.ClienteRepository;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Page<ClienteResponseDTO> listarClientes(Pageable pageable) {
		Page<Cliente> clientesPage = clienteRepository.findAll(pageable);

		List<ClienteResponseDTO> listaClientesDTO = clientesPage.getContent().stream().map(ClienteResponseDTO::new)
				.collect(Collectors.toList());

		return new PageImpl<>(listaClientesDTO, pageable, clientesPage.getTotalElements());
	}

	public ClienteResponseDTO buscarCliente(@Valid String id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
		return new ClienteResponseDTO(cliente);
	}

	 public ClienteResponseDTO buscarClientePorCpf(String cpf) {
	        Cliente cliente = clienteRepository.findByCpf(cpf).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
	        return new ClienteResponseDTO(cliente);
	    }
	public void cadastrar(@Valid ClienteRegisterDTO novoCliente) {
		if (clienteRepository.findByCpf(novoCliente.cpf()).isPresent()) {
			throw new CpfIndisponivelException("CPF já cadastrado!");
		}
		if (clienteRepository.findByCelular(novoCliente.celular()).isPresent()) {
			throw new CelularIndisponivelException("Celular já cadastrado!");
		}
		Cliente cliente = new Cliente(novoCliente);
		clienteRepository.save(cliente);
	}


    public void alterarDados(@Valid String cpf, @Valid ClienteUpdateDTO dadosAtualizado) {
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        if (dadosAtualizado.celular() != null && !Objects.equals(cliente.getCelular(), dadosAtualizado.celular())) {
            if (clienteRepository.findByCelular(dadosAtualizado.celular()).isPresent()) {
                throw new CelularIndisponivelException("Celular já cadastrado");
            }
            cliente.setCelular(dadosAtualizado.celular());
        }

        if (dadosAtualizado.nome() != null && !Objects.equals(cliente.getNome(), dadosAtualizado.nome())) {
            cliente.setNome(dadosAtualizado.nome());
        }

        if (dadosAtualizado.score() != cliente.getScore()) {
            cliente.setScore(dadosAtualizado.score());
        }

    	if (!Objects.equals(dadosAtualizado.negativado(), cliente.isNegativado())) {
			cliente.setNegativado(dadosAtualizado.negativado());
		}
        clienteRepository.save(cliente);
    }

	public void deletar(@Valid String id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
		cliente.setDeleted(true);
		clienteRepository.save(cliente);
	}

}