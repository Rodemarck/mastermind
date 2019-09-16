package com.rodemarck.mastermind.model.beans;


import com.rodemarck.mastermind.model.user.Usuario;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Jogo implements Serializable{
    private Usuario criador;
    private Tabuleiro tabuleiro;
    private LocalDateTime dataCricao;
    private String nome;
    private int[] resposta;
    private int id;

    public Jogo(Usuario criador, Tabuleiro tabuleiro, LocalDateTime dataCricao, String nome,int id, int ... resposta) {
        this.id = id;
        this.criador = criador;
        this.tabuleiro = tabuleiro;
        this.dataCricao = dataCricao;
        this.nome = nome;
        this.resposta = resposta;
    }

    public int getId() {
        return id;
    }


    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public LocalDateTime getDataCricao() {
        return dataCricao;
    }

    public void setDataCricao(LocalDateTime dataCricao) {
        this.dataCricao = dataCricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int[] getResposta() {
        return resposta;
    }

    public void setResposta(int[] resposta) {
        this.resposta = resposta;
    }
    
}