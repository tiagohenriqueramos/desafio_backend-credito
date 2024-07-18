package com.creditoservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.creditoservice.dto.ClienteApiFullResponseDTO;
import com.creditoservice.dto.ClienteApiResponseDTO;

@Service
public class ClienteApiService {


    @Autowired
    private RestTemplate restTemplate;

    public ClienteApiResponseDTO getClienteByCpf(String cpf) {
        String url = "http://cliente:8080/cliente/buscar/cpf/" + cpf;
        
        ClienteApiResponseDTO cliente = null;
        try {
            ResponseEntity<ClienteApiFullResponseDTO> response = restTemplate.getForEntity(url, ClienteApiFullResponseDTO.class);
            ClienteApiFullResponseDTO fullResponse = response.getBody();
            cliente = fullResponse != null ? fullResponse.getObjeto() : null;
        } catch (Exception e) {
        }

        return cliente;
    }
}
