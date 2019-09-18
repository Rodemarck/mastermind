package com.rodemarck.mastermind.connection.dao;

import com.rodemarck.mastermind.connection.DatabaseConnection;
import com.rodemarck.mastermind.model.user.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public static Usuario getByNome(String nome) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario u = null;
        try{
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "SELECT * FROM usuarios " +
                            "WHERE usuarios.nome=?"
            );
            stmt.setString(1,nome);
            rs = stmt.executeQuery();
            if(rs.next())
                u = new Usuario(rs);
        }catch (SQLException | ClassNotFoundException e){
            throw e;
        }finally{
            DatabaseConnection.getInstance().close(con,rs,stmt);
        }
        return u;
    }


    public static Usuario getById(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario u = null;
        try{
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "SELECT * FROM usuarios " +
                            "WHERE usuarios.id=?"
            );
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs.next())
                u = new Usuario(rs);
        }catch (SQLException | ClassNotFoundException e){
            throw e;
        }finally{
            DatabaseConnection.getInstance().close(con,rs,stmt);
        }
        return u;
    }



}
