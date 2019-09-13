/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodemarck.mastermind.controllers;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        String var = RequestContextHolder.currentRequestAttributes().getSessionId();
        RequestContextHolder.getRequestAttributes().setAttribute(var, "sexo", 0);
        System.out.println("index:"+var);
        System.out.println(RequestContextHolder.getRequestAttributes().getAttribute(var, 0));
        return mv;
    }
    
    @RequestMapping(value="/fazEscolha", method = RequestMethod.GET)
    public ResponseEntity<String> fazEscolha(String e1, String e2, String e3, String e4){
        if(e1.equals("red"))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    
}
