/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodemarck.mastermind.controllers;

import com.rodemarck.mastermind.model.Repositorio;
import com.rodemarck.mastermind.model.beans.Jogo;
import com.rodemarck.mastermind.model.beans.Tabuleiro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author RODEMARCK.MELOJ
 */
@RestController
public class IndexController {
    @RequestMapping("/")
    public ModelAndView index(@AuthenticationPrincipal UserDetails userDetails, String id) throws IOException{
        if(userDetails == null || id == null){
            return new ModelAndView("redirect:/jogosCriados");
        }
        Tabuleiro tab = null;
        for(Tabuleiro t : Repositorio.getInstance().getTabuleiros())
            if(t.getId() == Integer.parseInt(id)){
                tab = t;
                break;
            }
        if(tab == null)
            return new ModelAndView("redirect:/jogosCriados");
        
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("tabuleiro",tab);
        return mv;
    }
    
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
    
    @RequestMapping(value="/fazEscolha", method = RequestMethod.GET)
    public ResponseEntity<String> fazEscolha(String id, String index, String e1, String e2, String e3, String e4) throws IOException{
        int[] escolhas = new int[]{Tabuleiro.getVectorCores().indexOf(e1),
                                    Tabuleiro.getVectorCores().indexOf(e2),
                                    Tabuleiro.getVectorCores().indexOf(e3),
                                    Tabuleiro.getVectorCores().indexOf(e4)
        };
        int TabuleiroId = Integer.parseInt(id);
        int in = Integer.parseInt(index) - 1;
        
        Repositorio.getInstance().TabuleiroPorId(TabuleiroId).setarMatriz(in, escolhas);
        Repositorio.escreve();
        if(e1.equals("red"))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    
}
