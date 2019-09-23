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

    public static void main(String[] args) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        String s = b.encode("1234");
        System.out.println(b.matches("1234",s));
        System.out.println(s);
        //SpringApplication.run(ProjectApplication.class, args);

    }

}
