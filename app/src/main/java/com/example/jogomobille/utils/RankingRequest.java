package com.example.jogomobille.utils;

public class RankingRequest {

    int pontuacao, fase, idUsuario;

    public RankingRequest(int pontuacao, int fase, int idUsuario) {
        this.pontuacao = pontuacao;
        this.fase = fase;
        this.idUsuario = idUsuario;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
