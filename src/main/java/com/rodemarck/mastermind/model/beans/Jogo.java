package com.rodemarck.mastermind.model.beans;


import com.rodemarck.mastermind.connection.dao.UsuarioDAO;
import com.rodemarck.mastermind.model.user.Usuario;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Jogo implements Serializable{
    private Usuario criador;
    private LocalDateTime dataCricao;
    private int[] resposta;
    private int id;
    private static int IDENTIFICATOR = 1;
    private String respostaString;

    public Jogo(ResultSet rs) throws SQLException, ClassNotFoundException {
        this.dataCricao = LocalDateTime.parse(rs.getString("jogo.dataCriacao"));
        this.id = rs.getInt("jogo.id");
        this.respostaString = rs.getString("jogo.respostas");
        this.resposta = new int[4];
        for(int x=0; x<4; x++)
            this.resposta[x] = Integer.parseInt(""+this.respostaString.charAt(x));
        this.criador = UsuarioDAO.getById(rs.getInt("jogo.id_criador"));
    }
    public Jogo(Usuario criador, LocalDateTime dataCricao,  int ... resposta) {
        this.id = id;
        this.criador = criador;
        this.dataCricao = dataCricao;
        this.resposta = resposta;
        this.respostaString = "";
        for(int i=0;i<4;i++)
            this.respostaString+=i;
    }

    public int getId() {
        return id;
    }


    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public LocalDateTime getDataCricao() {
        return dataCricao;
    }

    public void setDataCricao(LocalDateTime dataCricao) {
        this.dataCricao = dataCricao;
    }



    public int[] getResposta() {
        return resposta;
    }

    public void setResposta(int[] resposta) {
        this.resposta = resposta;
    }

    public String getRespostaString() {
        return respostaString;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                ", dataCricao=" + dataCricao +
                ", resposta=" + Arrays.toString(resposta) +
                ", id=" + id +
                ", respostaString='" + respostaString + '\'' +
                "criador=\n\t" + criador +
                "\n}";
    }
}
