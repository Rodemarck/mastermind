package com.rodemarck.mastermind.controllers;

import com.rodemarck.mastermind.connection.dao.JogoDAO;
import com.rodemarck.mastermind.model.beans.Jogo;
import com.rodemarck.mastermind.model.user.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
public class jogosCriadosController {
    @RequestMapping("/jogosCriados")
    public ModelAndView jogos(HttpSession session){
        System.out.println(session.getAttribute("conta"));
        ArrayList<Jogo> jogos = null;
        try{
            jogos = JogoDAO.listar();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("jogosCriados");
        mv.addObject("jogos", jogos);
        return mv;
    }

    @RequestMapping("/criarJogo")
    public ResponseEntity<String> criarJogo(HttpSession session, int c1, int c2, int c3, int c4){
        int[] respostas = new int[]{c1,c2,c3,c4};
        try {
            JogoDAO.cadastrar(new Jogo(((Usuario)session.getAttribute("conta")),LocalDateTime.now(),respostas));
        } catch (SQLException | ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("foi porra",HttpStatus.ACCEPTED);
    }
}
