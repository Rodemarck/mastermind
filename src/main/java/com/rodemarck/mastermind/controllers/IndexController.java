/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodemarck.mastermind.controllers;

import com.rodemarck.mastermind.connection.dao.JogoDAO;
import com.rodemarck.mastermind.connection.dao.TabuleiroDAO;
import com.rodemarck.mastermind.model.beans.Jogo;
import com.rodemarck.mastermind.model.beans.Tabuleiro;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import com.rodemarck.mastermind.model.user.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 *
 * @author RODEMARCK.MELOJ
 */
@RestController
public class IndexController {
    @RequestMapping("/")
    public ModelAndView index(HttpSession session, String id) throws SQLException, ClassNotFoundException {
        if(id == null){
            return new ModelAndView("redirect:/salao");
        }
        Jogo tab = null;
        int jId = Integer.parseInt(id);
        for(Jogo t : JogoDAO.listar())
            if(t.getId() == jId){
                tab = t;
                break;
            }
        if(tab == null)
            return new ModelAndView("redirect:/salao");
        System.out.println((Usuario) session.getAttribute("conta"));
        Tabuleiro t = TabuleiroDAO.tabuleiroDoJogo((Usuario) session.getAttribute("conta"),jId);
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("tabuleiro",t);
        return mv;
    }
    
    @RequestMapping(value="/fazEscolha", method = RequestMethod.GET)
    public ResponseEntity<String> fazEscolha(HttpSession session, int id, int index, int e1, int e2, int e3, int e4) {
        int[] escolhas = new int[]{ e1,e2,e3,e4};
        System.out.println("id>>"+id);
        System.out.println("index>>"+index);
        System.out.print("vector>>"+Arrays.toString(escolhas));
        System.out.println();
        index --;
        try{
            Tabuleiro t = TabuleiroDAO.tabuleiroDoJogo((Usuario) session.getAttribute("conta"),id);
            if(t.valida()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }if(t.getIndex()<=0){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            t.setarMatriz(escolhas);
            if(t.valida()){
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(SQLException | ClassNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }finally{}

    }

    @RequestMapping("/carrega")
    private String fazEscolhas(HttpSession session,int id,Model model) throws IOException, SQLException, ClassNotFoundException {
        Tabuleiro t = TabuleiroDAO.tabuleiroDoJogo((Usuario) session.getAttribute("conta"),id);
        model.addAttribute("tabuleiro",t);
        return "index :: #linhas";
    }
}
