package com.creditoservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteApiFullResponseDTO {
    @JsonProperty("status")
    private int status;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("objeto")
    private ClienteApiResponseDTO objeto;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ClienteApiResponseDTO getObjeto() {
        return objeto;
    }

    public void setObjeto(ClienteApiResponseDTO objeto) {
        this.objeto = objeto;
    }
}