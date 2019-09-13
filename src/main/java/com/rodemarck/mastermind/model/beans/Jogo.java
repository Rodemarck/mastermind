package com.rodemarck.mastermind.model.beans;

import com.rodemarck.mastermind.model.Usuario;

import java.time.LocalDateTime;

public class Jogo {
    private Usuario criador;
    private Tabuleiro tabuleiro;
    private LocalDateTime dataCricao;
    private int[] resposta;
}
