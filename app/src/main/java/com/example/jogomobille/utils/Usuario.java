package com.example.jogomobille.utils;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("id")
    private int idUsuario;
    @SerializedName("nomeUsuario")
    private String nomeUsuario;

    public Usuario()
    {
        this.idUsuario = 0;
        this.nomeUsuario = "";
    }

    public Usuario(int idUsuario, String nomeUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;

    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
}