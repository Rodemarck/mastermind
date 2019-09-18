package com.rodemarck.mastermind.controllers;

import com.rodemarck.mastermind.model.Repositorio;
import com.rodemarck.mastermind.model.beans.Jogo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class jogosCriadosController {
    @RequestMapping("/jogosCriados")
    public ModelAndView jogos(){
        ArrayList<Jogo> jogos = null;
        try{
            jogos = Repositorio.getInstance().getJogos();
        }catch (IOException e){
            jogos = new ArrayList<>();
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("jogosCriados");
        mv.addObject("jogos", jogos);
        return mv;
    }
}
