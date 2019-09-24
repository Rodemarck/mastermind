package com.rodemarck.mastermind.controllers;

import com.rodemarck.mastermind.connection.dao.UsuarioDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/cadastrar")
    public ResponseEntity<String> cadastrar(String login, String password) {
        try {
            UsuarioDAO.cadastrar(login, password);
        } catch (ClassNotFoundException | SQLException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
