package com.rodemarck.mastermind.model;

import com.rodemarck.mastermind.model.beans.Jogo;
import com.rodemarck.mastermind.model.beans.Tabuleiro;
import com.rodemarck.mastermind.model.user.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Repositorio implements Serializable {

    private static Repositorio instance;

    private final ArrayList<Usuario> usuarios = new ArrayList<>();
    private final ArrayList<Jogo> jogos = new ArrayList<>();
    private final ArrayList<RepositorioTabuleiro> tabuleiros = new ArrayList<>();

    private Repositorio() {
    }

    private static Repositorio ler() throws IOException, ClassNotFoundException {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            f = new File("arquivos\\repositorio.rep");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            return (Repositorio) ois.readObject();
        } catch (IOException e) {
            throw e;
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (ois != null) {
                ois.close();
            }
        }
    }

    public static void escreve() throws IOException {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            f = new File("arquivos\\repositorio.rep");
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(instance);
        } catch (IOException e) {
            throw e;
        } finally {
            if (fos != null) {
                fos.close();
            }
            if (oos != null) {
                oos.close();
            }
        }
    }

    public static Repositorio getInstance() throws IOException {
        if (instance == null) {
            try {
                instance = ler();
            } catch (IOException | ClassNotFoundException e) {
                instance = new Repositorio();
                try {
                    escreve();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return instance;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Jogo> getJogos() {
        return jogos;
    }

    public ArrayList<RepositorioTabuleiro> getTabuleiros() {
        return tabuleiros;
    }

    
    public Usuario procurarPorLogin(String s) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(s)) {
                return u;
            }
        }
        return null;
    }

    public Jogo procuraJogoPorId(int jId){
        for(Jogo j:jogos)
            if(j.getId() == jId)
                return j;
        return null;
    }
    


    public Tabuleiro getPartida(String username, int jId) {
        Jogo j = procuraJogoPorId(jId);
        for(RepositorioTabuleiro r: tabuleiros)
            if(r.getUsuario().getLogin().equals(username))
                return r.getTabuleiro(j,jId);
        RepositorioTabuleiro t = new RepositorioTabuleiro(procurarPorLogin(username));
        tabuleiros.add(t);
        return t.getTabuleiro(j,jId);
    }
}
