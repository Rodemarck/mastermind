package com.rodemarck.mastermind.controllers;

import com.rodemarck.mastermind.connection.dao.UsuarioDAO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@RestController
public class CadastroController {
    @RequestMapping(value = "/cadastro")
    public ModelAndView cadastro(){
        ModelAndView mv = new ModelAndView("cadastro");
        return mv;
    }

    @PostMapping(value = "/cadastrar")
    public ModelAndView cadastrar(String login, String password){
        try{
            UsuarioDAO.cadastrar(login,password);
        }catch (ClassNotFoundException | SQLException  e){
            return new ModelAndView("cadastro");
        }
        return new ModelAndView("redirect:/");
    }
}
