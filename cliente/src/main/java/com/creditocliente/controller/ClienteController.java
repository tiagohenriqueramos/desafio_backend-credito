package com.creditocliente.controller;

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

import com.creditocliente.dto.ClienteRegisterDTO;
import com.creditocliente.dto.ClienteResponseDTO;
import com.creditocliente.dto.ClienteUpdateDTO;
import com.creditocliente.exception.ApiResponse;
import com.creditocliente.service.ClienteService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ApiResponse<ClienteResponseDTO>> buscar(@PathVariable @Valid String id) {
        ClienteResponseDTO fornecedor = clienteService.buscarCliente(id);
        return ResponseEntity.ok()
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Busca realizada com sucesso!", fornecedor));
    }

    @GetMapping("/buscar/cpf/{cpf}")
    public ResponseEntity<ApiResponse<ClienteResponseDTO>> buscarPorCpf(@PathVariable @Valid String cpf) {
        ClienteResponseDTO cliente = clienteService.buscarClientePorCpf(cpf);
        return ResponseEntity.ok()
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Busca realizada com sucesso!", cliente));
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<Page<ClienteResponseDTO>>> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        Page<ClienteResponseDTO> clientesPage = clienteService.listarClientes(paginacao);
        ApiResponse<Page<ClienteResponseDTO>> response = new ApiResponse<>(HttpStatus.OK.value(),
                "Listagem concluída com sucesso!", clientesPage);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ApiResponse<ClienteRegisterDTO>> cadastrar(@RequestBody @Valid ClienteRegisterDTO novoCliente) {
        clienteService.cadastrar(novoCliente);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Cliente Cadastrado com sucesso!", novoCliente));
    }

    @PutMapping("/atualizar/{cpf}")
    public ResponseEntity<ApiResponse<ClienteUpdateDTO>> alterarDados(@PathVariable @Valid String cpf, @RequestBody @Valid ClienteUpdateDTO clienteAtualizado){
        clienteService.alterarDados(cpf, clienteAtualizado);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(),"Dados alterado com sucesso!", clienteAtualizado));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> deletar(@PathVariable @Valid String id) {
        clienteService.deletar(id);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(),"Cliente deletado com sucesso!"));
    }
}
