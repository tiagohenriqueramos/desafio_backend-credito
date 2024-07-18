package com.creditoservice.exception;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ApiResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int status;
    private String mensagem;
    private String timestamp; // Alterado para String formatada
    private T objeto;

    public ApiResponse() {}

    public ApiResponse(int status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
        this.timestamp = formatarDataHora(Instant.now());
    }

    public ApiResponse(int status, T objeto) {
        this.status = status;
        this.objeto = objeto;
        this.timestamp = formatarDataHora(Instant.now());
    }

    public ApiResponse(int status, String mensagem, T objeto) {
        this.status = status;
        this.mensagem = mensagem;
        this.timestamp = formatarDataHora(Instant.now());
        this.objeto = objeto;
    }

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

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }

    private String formatarDataHora(Instant instant) {
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.of("America/Sao_Paulo"));
        return ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}