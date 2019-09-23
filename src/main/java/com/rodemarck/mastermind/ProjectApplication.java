package com.rodemarck.mastermind;

import com.rodemarck.mastermind.connection.dao.JogoDAO;
import com.rodemarck.mastermind.connection.dao.UsuarioDAO;
import com.rodemarck.mastermind.model.beans.Jogo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //UsuarioDAO.cadastrar("fefe","sexo");
        //System.out.println(new BCryptPasswordEncoder().encode("123"));
        SpringApplication.run(ProjectApplication.class, args);

    }

}
