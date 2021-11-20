package com.example.jogomobille;

public class LoginResponse {
    String message;
    int idUsuario;
    String nomeUsuario;
    String token;

    public LoginResponse(String message, int idUsuario, String nomeUsuario, String token) {
        this.message = message;
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
