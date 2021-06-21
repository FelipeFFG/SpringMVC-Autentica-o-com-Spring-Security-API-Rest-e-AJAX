package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller                                                                   //para definir que uma calsse é controller, usamos a notação @Controler
public class HelloController {

    @GetMapping("/hello")                                                  //GetMapping indica o path da requisição para chamar a  Action.
    public String hello(Model model) {
        model.addAttribute("nome", "Mundo");                            //manda informações que a view pode acessar,atravez de sprint expression leguage
        return "hello";


   /* @GetMapping("/hello")
    public String hello(HttpServletRequest request){       //HttpserveletRequest utiliza a camada de serverless pra fazer isso
        request.setAttribute("nome","Mundo");              //manda informaçoes para nossa view
        return "hello";
    }*/

    }
}
