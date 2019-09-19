package com.rodemarck.mastermind.connection.dao;

import com.rodemarck.mastermind.connection.DatabaseConnection;
import com.rodemarck.mastermind.model.beans.Jogo;
import com.rodemarck.mastermind.model.user.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class JogoDAO {

    public static Jogo getById(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Jogo j = null;
        try{
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "SELECT * FROM usuarios " +
                            "WHERE usuarios.id=?"
            );
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs.next())
                j = new Jogo(rs);
        }catch (SQLException | ClassNotFoundException e ){
            throw e;
        }finally {
            DatabaseConnection.getInstance().close(con,rs,stmt);
        }
        return j;
    }

    public static void cadastrar(Jogo jogo) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "INSERT INTO jogo (id_criador,dataCriacao,nome,respostas) VALUES (?,?,?,?)"
            );
            stmt.setInt(1,jogo.getCriador().getId());
            stmt.setString(2,jogo.getDataCricao().toString());
            stmt.setString(3,jogo.getNome());
            stmt.setString(4,jogo.getRespostaString());
            stmt.executeUpdate();
        }catch (SQLException | ClassNotFoundException e){
            throw e;
        }finally{
            DatabaseConnection.getInstance().close(con,stmt);
        }
    }

    public static void deleteById(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "DELETE FROM jogo WHERE jogo.id=?"
            );
            stmt.setInt(1,id);
            stmt.executeUpdate();
        }catch (SQLException | ClassNotFoundException e){
            throw e;
        }finally{
            DatabaseConnection.getInstance().close(con,stmt);
        }
    }


}