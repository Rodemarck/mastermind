package com.rodemarck.mastermind.model.beans;

import com.rodemarck.mastermind.model.user.Usuario;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Tabuleiro implements Serializable{
    private final static ArrayList<String> vectorCores = new ArrayList<>(Arrays.asList(new String[]{"white","red","green","blue","yellow","pink","turquoise","brown","purple"}));
    private int id;
    private int[][] matriz;
    private int[][] pedra;
    private Usuario usuario;
    private LocalDateTime ultimoJogada;
    private boolean terminado;

    public Tabuleiro(Usuario u,int id) {
        this.id = id;
        this.usuario = u;
        this.terminado = false;
        this.matriz = new int[10][4];
        this.pedra = new int[10][4];
        for(int y=0 ; y<10 ; y++)
            for (int x = 0; x < 4; x++){
                this.matriz[y][x] = 0;
                this.pedra[y][x] = 0;
            }
    }

    public void setarMatriz(int index, int ... vector){
        for (int x = 0; x < 4; x++)
            this.matriz[index][x] = vector[x];
        verifica(index, vector);
    }

    public static ArrayList<String> getVectorCores() {
        return vectorCores;
    }

    public String cor(int i){
        return vectorCores.get(i);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public int[][] getPedra() {
        return pedra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getUltimoJogada() {
        return ultimoJogada;
    }

    public void setUltimoJogada(LocalDateTime ultimoJogada) {
        this.ultimoJogada = ultimoJogada;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }
    

    public void verifica(int index, int[] resposta) {
        int pretas = 0;
        int brancas = 0;
        int aux1,aux2;
        for(int i=0 ; i<4 ; i++)
            if(matriz[index][i] == resposta[i])
                pretas ++;

        if(pretas == 4)
            for(int i=0 ; i<4; i++)
                this.pedra[index][i] = 2;

        for(int x=0; x<10; x++){
            aux1 = 0;
            aux2 = 0;
            for(int i=0; i<4; i++){
                if(resposta[i] == x)
                    aux1++;
                if(matriz[index][i] == x)
                    aux2++;
            }
            brancas += (aux2>aux1) ? aux1 : aux2;
        }
        brancas -= pretas;
        for(int i=0 ; i<4; i++)
            this.pedra[index][i] = 0;
        aux1 = 0;
        aux2 = 0;
        while (aux1 < pretas)
            this.pedra[index][aux1++] = 2;
        while (aux2++ < brancas)
            this.pedra[index][aux1++] = 1;
    }

    public boolean valida(int[] resposta){
        int cont;
        for(int y=0 ; y<10 ; y++) {
            cont = 0;
            for (int x = 0; x < 4; x++)
                if(this.pedra[y][x] == resposta [x])
                    cont++;
            if(cont == 4)
                return true;
        }
        return  false;
    }
}
