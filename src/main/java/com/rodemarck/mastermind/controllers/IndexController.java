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
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        if(id == null){
            return new ModelAndView("redirect:/jogosCriados");
        }
        Jogo tab = null;
        int jId = Integer.parseInt(id);
        for(Jogo t : Repositorio.getInstance().getJogos())
            if(t.getId() == jId){
                tab = t;
                break;
            }
        if(tab == null)
            return new ModelAndView("redirect:/jogosCriados");
        Tabuleiro t = Repositorio.getInstance().getPartida(userDetails.getUsername(),jId);
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("tabuleiro",t);
        return mv;
    }
    
    @RequestMapping(value="/fazEscolha", method = RequestMethod.GET)
    public ResponseEntity<String> fazEscolha(@AuthenticationPrincipal UserDetails userDetails, int id, int index, int e1, int e2, int e3, int e4) {
        int[] escolhas = new int[]{ e1,e2,e3,e4};
        System.out.println("id>>"+id);
        System.out.println("index>>"+index);
        System.out.print("vector>>"+Arrays.toString(escolhas));
        System.out.println();
        index --;
        try{
            Tabuleiro t = Repositorio.getInstance().getPartida(userDetails.getUsername(),id);
            if(t.valida()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }if(t.getIndex()<=0){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            t.setarMatriz(escolhas);
            try{
                Repositorio.escreve();
            }catch(IOException e){}
            if(t.valida()){
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }return new ResponseEntity<>(HttpStatus.OK);
        }catch(IOException e){
            
        }finally{
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/carrega")
    private String fazEscolhas(@AuthenticationPrincipal UserDetails userDetails,String id,Model model) throws IOException {
        int TabuleiroId = Integer.parseInt(id);
        Tabuleiro t = Repositorio.getInstance().getPartida(userDetails.getUsername(),TabuleiroId);
        model.addAttribute("tabuleiro",t);
        return "index :: #linhas";
    }
}
