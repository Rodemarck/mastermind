package com.rodemarck.mastermind;

import com.rodemarck.mastermind.connection.dao.JogoDAO;
import com.rodemarck.mastermind.connection.dao.UsuarioDAO;
import com.rodemarck.mastermind.model.beans.Jogo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        try {
            UsuarioDAO.cadastrar("rode","123");
            JogoDAO.cadastrar(new Jogo(UsuarioDAO.getById(0), LocalDateTime.now(), "nome", 1, new int[]{1, 2, 3, 4, 5}));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SpringApplication.run(ProjectApplication.class, args);

    }

}
