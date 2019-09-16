/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodemarck.mastermind.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author RODEMARCK.MELOJ
 */
@RestController
public class LoginController {
    @RequestMapping("/logar")
    public ModelAndView logar(){
        ModelAndView mv = new ModelAndView("logar");
        return mv;
    }
}
