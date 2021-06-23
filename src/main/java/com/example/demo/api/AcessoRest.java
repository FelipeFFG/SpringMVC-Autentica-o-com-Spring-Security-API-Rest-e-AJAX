package com.example.demo.api;

import com.example.demo.interceptor.InterceptadorDeAcessos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("acessos")
@RestController
public class AcessoRest {


    @GetMapping
    public List<InterceptadorDeAcessos.Acesso> getAcesso(){
            return InterceptadorDeAcessos.acessos;
    }
}
