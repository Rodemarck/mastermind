package com.rodemarck.mastermind.model;

import com.rodemarck.mastermind.model.beans.Jogo;
import com.rodemarck.mastermind.model.beans.Tabuleiro;
import com.rodemarck.mastermind.model.user.Usuario;

import java.util.ArrayList;
import java.util.Random;

public class RepositorioTabuleiro {
    private Usuario usuario;
    private static int id = 1;
    private ArrayList<Tabuleiro> tabuleiros;

    public RepositorioTabuleiro(Usuario usuario) {
        this.usuario = usuario;
        this.tabuleiros = new ArrayList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ArrayList<Tabuleiro> getTabuleiros() {
        return tabuleiros;
    }

    public Tabuleiro getTabuleiro(Jogo j, int jId){
        for(Tabuleiro t: tabuleiros)
            if(t.getJogo().getId() == jId)
                return t;
        Tabuleiro t = new Tabuleiro(usuario, j,0);
        tabuleiros.add(t);
        return t;
    }
}
