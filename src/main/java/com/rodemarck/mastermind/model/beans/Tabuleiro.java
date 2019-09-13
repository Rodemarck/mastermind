package com.rodemarck.mastermind.model.beans;

import java.util.ArrayList;
import java.util.Arrays;

public class Tabuleiro{
    private final static ArrayList<String> vectorCores = new ArrayList<>(Arrays.asList(new String[]{"white","red","green","blue","yellow","pink","turquoise","brown","purple"}));
    private int[][] matriz = new int[5][10];
    private int[][] pedra = new int[5][10];

    public Tabuleiro() {
        for(int y=0 ; y<10 ; y++)
            for (int x = 0; x < 5; x++){
                this.matriz[x][y] = 0;
                this.pedra[x][y] = 0;
            }
    }

    public void setarMatriz(int index, int ... vector){
        for (int x = 0; x < 5; x++)
            this.matriz[x][index] = vector[x];
        verifica(index, vector);
    }

    public static ArrayList<String> getVectorCores() {
        return vectorCores;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public int[][] getPedra() {
        return pedra;
    }

    public void verifica(int index, int[] resposta) {
        int pretas = 0;
        int brancas = 0;
        int aux1,aux2;
        for(int i=0 ; i<5 ; i++)
            if(matriz[i][index] == resposta[i])
                pretas ++;

        if(pretas == 5)
            for(int i=0 ; i<5; i++)
                this.pedra[i][index] = 2;

        for(int x=0; x<10; x++){
            aux1 = 0;
            aux2 = 0;
            for(int i=0; i<5; i++){
                if(resposta[i] == x)
                    aux1++;
                if(matriz[i][index] == x)
                    aux2++;
            }
            brancas += (aux2>aux1) ? aux1 : aux2;
        }
        brancas -= pretas;
        for(int i=0 ; i<5; i++)
            this.pedra[i][index] = 0;
        aux1 = 0;
        aux2 = 0;
        while (aux1 < pretas)
            this.pedra[aux1++][index] = 2;
        while (aux2++ < brancas)
            this.pedra[aux1++][index] = 1;
    }

    public boolean valida(int[] resposta){
        int cont;
        for(int y=0 ; y<10 ; y++) {
            cont = 0;
            for (int x = 0; x < 5; x++)
                if(this.pedra[x][y] == resposta [x])
                    cont++;
            if(cont == 5)
                return true;
        }
        return  false;
    }
}
