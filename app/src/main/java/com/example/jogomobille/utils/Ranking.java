package com.example.jogomobille.utils;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class Ranking implements Serializable {
    @SerializedName("idUsuario")
    private int idUsuario;

    @SerializedName("pontuacao")
    private int pontuacao;

    @SerializedName("fase")
    private int fase;

    @SerializedName("usuario")
    private Usuario usuario;

    public Ranking(int idUsuario, int pontuacao, int fase, Usuario usuario) {
        this.idUsuario = idUsuario;
        this.pontuacao = pontuacao;
        this.fase = fase;
        this.usuario = usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public int getFase() {
        return fase;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
