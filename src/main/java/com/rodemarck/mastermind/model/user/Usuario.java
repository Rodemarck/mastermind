/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodemarck.mastermind.model.user;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author RODEMARCK.MELOJ
 */
public class Usuario implements Serializable{
    private int id;
    private String login;
    private String senha;

    public Usuario(ResultSet rs) throws SQLException {
        this.id = rs.getInt("usuarios.id");
        this.login = rs.getString("usuarios.login");
        this.senha = rs.getString("usuarios.senha");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}
