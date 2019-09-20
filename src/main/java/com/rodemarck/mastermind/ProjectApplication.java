package com.rodemarck.mastermind;

import com.rodemarck.mastermind.model.Repositorio;
import com.rodemarck.mastermind.model.beans.Jogo;
import com.rodemarck.mastermind.model.beans.Tabuleiro;
import com.rodemarck.mastermind.model.user.Usuario;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        try {
            Repositorio.getInstance().getUsuarios().add(new Usuario("rode", "123"));
            Repositorio.getInstance().getJogos().add(new Jogo(Repositorio.getInstance().getUsuarios().get(0), LocalDateTime.now(), "nome", 1, new int[]{1, 2, 3, 4, 5}));
        } catch (IOException e) {
            System.out.println(e);
        }
        SpringApplication.run(ProjectApplication.class, args);

    }

}
