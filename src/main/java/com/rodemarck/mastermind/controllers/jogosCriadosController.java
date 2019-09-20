package com.rodemarck.mastermind.controllers;

import com.rodemarck.mastermind.connection.dao.JogoDAO;
import com.rodemarck.mastermind.model.beans.Jogo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class jogosCriadosController {
    @RequestMapping("/jogosCriados")
    public ModelAndView jogos(){
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
}
