package com.rodemarck.mastermind.model.beans;


import com.rodemarck.mastermind.model.user.Usuario;
import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class Jogo implements Serializable{
    private Usuario criador;
    private LocalDateTime dataCricao;
    private String nome;
    private int[] resposta;
    private int id;
    private static int IDENTIFICATOR = 1;
    private String respostaString;

    public Jogo(ResultSet rs){

    }
    public Jogo(Usuario criador, LocalDateTime dataCricao, String nome,int id, int ... resposta) {
        this.id = id;
        this.criador = criador;
        this.dataCricao = dataCricao;
        this.nome = nome;
        this.resposta = resposta;
        this.respostaString = "";
        for(int i=0;i<4;i++)
            this.respostaString+=i;
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

    public String getRespostaString() {
        return respostaString;
    }
}
