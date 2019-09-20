package com.rodemarck.mastermind.model.beans;

import com.rodemarck.mastermind.model.user.Usuario;
import java.io.Serializable;
import java.sql.ResultSet;
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
    private int index;
    private Jogo jogo;
    

    public Tabuleiro(Usuario u, Jogo jogo, int id) {
        this.id = id;
        this.usuario = u;
        this.matriz = new int[10][4];
        this.pedra = new int[10][4];
        this.index = 9;
        this.jogo = jogo;
        for(int y=0 ; y<10 ; y++)
            for (int x = 0; x < 4; x++){
                this.matriz[y][x] = 0;
                this.pedra[y][x] = 0;
            }
    }

    public Tabuleiro(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setarMatriz(int ... vector){
        System.out.println("chegou >>"+Arrays.toString(vector));
        for(int x=0;x<4;x++)
            this.matriz[index][x] = vector[x];
        verifica(index);
    }

    public static ArrayList<String> getVectorCores() {
        return vectorCores;
    }
    
    public String cor(int i){
        return vectorCores.get(i);
    }
    
    public String pedra(int i){
        switch(i){
            case 1:
                return "Resposta False";
            case 2:
                return "Resposta True";
            default:
                return "Resposta None";
        }
    }
    
    public int getId() {
        return id;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public void verifica(int index) {
        int pretas = 0;
        int brancas = 0;
        int aux1,aux2;
        for(int i=0 ; i<4 ; i++)
            if(matriz[index][i] == this.jogo.getResposta()[i])
                pretas ++;

        if(pretas == 4)
            for(int i=0 ; i<4; i++)
                this.pedra[index][i] = 2;

        for(int x=0; x<10; x++){
            aux1 = 0;
            aux2 = 0;
            for(int i=0; i<4; i++){
                if(this.jogo.getResposta()[i] == x)
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
        this.index--;
    }

    public boolean valida(){
        int cont;
        System.out.println(this.jogo);
        System.out.println("respostas{\n\t");
        for(int s:this.jogo.getResposta())
            System.out.print(s+",");
        System.out.println("\n}");
        for(int y=0 ; y<10 ; y++) {
            cont = 0;
            
            for (int x = 0; x < 4; x++){
                System.out.print(this.matriz[y][x]+",");
                if(this.matriz[y][x] == this.jogo.getResposta()[x])
                    cont++;
            }
            System.out.println("");
            if(cont == 4)
                return true;
        }
        return  false;
    }

    public String getMatrizString(){
        StringBuilder s= new StringBuilder();
        for(int[] y:matriz)
            for(int x:y)
                s.append(s);
        return s.toString();
    }

    public String getPedraString(){
        StringBuilder s= new StringBuilder();
        for(int[] y:pedra)
            for(int x:y)
                s.append(s);
        return s.toString();
    }
}
