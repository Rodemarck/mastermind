package com.rodemarck.mastermind.connection.dao;

import com.rodemarck.mastermind.connection.DatabaseConnection;
import com.rodemarck.mastermind.model.beans.Tabuleiro;
import com.rodemarck.mastermind.model.user.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TabuleiroDAO {

    public static void cadastrar(Tabuleiro t) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "INSERT INTO tabuleiro (matriz,pedras,indice,id_jogo,id_jogador) VALUES (?,?,?,?,?)"
            );
            stmt.setString(1, t.getMatrizString());
            stmt.setString(2, t.getPedraString());
            stmt.setInt(3, t.getIndex());
            stmt.setInt(4, t.getJogo().getId());
            stmt.setInt(5, t.getUsuario().getId());

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            DatabaseConnection.getInstance().close(con, stmt);
        }
    }

    public static Tabuleiro getById(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Tabuleiro t = null;
        try {
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "SELECT * FROM tabuleiro "
                            + "WHERE tabuleiro.id=?"
            );
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                t = new Tabuleiro(rs);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            DatabaseConnection.getInstance().close(con, rs, stmt);
        }
        return t;
    }

    public static ArrayList<Tabuleiro> listar() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Tabuleiro> list = new ArrayList<>();
        try {
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "SELECT * FROM tabuleiro"
            );
            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Tabuleiro(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            DatabaseConnection.getInstance().close(con, rs, stmt);
        }
        return list;
    }

    public static ArrayList<Tabuleiro> listarByJogador(int id_jogador) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Tabuleiro> list = new ArrayList<>();
        try {
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "SELECT * FROM tabuleiro "
                            + "where tabuleiro.id_jogador=?"
            );
            stmt.setInt(1, id_jogador);
            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Tabuleiro(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            DatabaseConnection.getInstance().close(con, rs, stmt);
        }
        return list;
    }

    public static ArrayList<Tabuleiro> listarByJogo(int id_jogo) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Tabuleiro> list = new ArrayList<>();
        try {
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "SELECT * FROM tabuleiro "
                            + "where tabuleiro.id_jogo=?"
            );
            stmt.setInt(1, id_jogo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Tabuleiro(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            DatabaseConnection.getInstance().close(con, rs, stmt);
        }
        return list;
    }

    public static void fazerJogada(Tabuleiro t) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "UPDATE tabuleiro SET "
                            + "tabuleiro.matriz=? "
                            + "tabuleiro.pedras=? "
                            + "tabuleiro.indice=? "
                            + "WHERE tabuleiro.id=?"
            );
            stmt.setString(1, t.getMatrizString());
            stmt.setString(2, t.getPedraString());
            stmt.setInt(3, t.getIndex());
            stmt.setInt(4, t.getId());
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            DatabaseConnection.getInstance().close(con, stmt);
        }
    }

    public static Tabuleiro tabuleiroDoJogo(Usuario usuario, int id_jogo) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Tabuleiro t = null;
        try {
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "SELECT * FROM tabuleiro " +
                            "INNER JOIN jogo " +
                            "ON tabuleiro.id_jogo=jogo.id " +
                            "WHERE tabuleiro.id_jogador=? " +
                            "AND jogo.id=? " +
                            "ORDER BY tabuleiro.id DESC " +
                            "LIMIT 1"
            );
            stmt.setInt(1, usuario.getId());
            stmt.setInt(2, id_jogo);
            rs = stmt.executeQuery();
            if (rs.next())
                t = new Tabuleiro(rs);
            else{
                t = new Tabuleiro(usuario, JogoDAO.getById(id_jogo));
                cadastrar(t);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            DatabaseConnection.getInstance().close(con, rs, stmt);
        }
        return t;
    }

    public static ArrayList<Tabuleiro> listarByJogadorAndJogo(int id_jogador, int id_jogo) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Tabuleiro> list = new ArrayList<>();
        try {
            con = DatabaseConnection.getInstance().getConnection();
            stmt = con.prepareStatement(
                    "SELECT FROM tabuleiro "+
                    "WHERE tabuleiro.id_jogador=?"
            );
            stmt.setInt(1, id_jogador);
            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Tabuleiro(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            DatabaseConnection.getInstance().close(con, rs, stmt);
        }
        return list;
    }
}
