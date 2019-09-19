package com.rodemarck.mastermind.connection.dao;


import com.rodemarck.mastermind.connection.DatabaseConnection;
import com.rodemarck.mastermind.model.beans.Tabuleiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TabuleiroDAO {
    public static void cadastrar(Tabuleiro t) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "INSERT INTO tabuleiro (matriz,pedras,indice,id_jogo,id_jogador) VALUES (?,?,?,?,?)"
            );
            stmt.setString(1,t.getMatrizString());
            stmt.setString(2,t.getPedraString());
            stmt.setInt(3,t.getIndex());
            stmt.setInt(4,t.getJogo().getId());
            stmt.setInt(5,t.getUsuario().getId());
        }catch (SQLException | ClassNotFoundException e){
            throw e;
        }finally{
            DatabaseConnection.getInstance().close(con,stmt);
        }
    }
}
