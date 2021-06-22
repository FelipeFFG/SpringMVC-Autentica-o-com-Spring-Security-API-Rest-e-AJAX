package com.example.demo.controller;

import javax.validation.Valid;

import com.example.demo.dto.RequisicaoNovoPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.User;
import com.example.demo.repository.PedidosRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pedido")                    //definir uma parte da rota
public class PedidoControler {


    @Autowired                                  //pedir pro Spring criar uma classe
    private PedidosRepository pedidosRepository;


    @Autowired
    private UserRepository userRepository;

    @GetMapping("formulario")
    public String formulario(RequisicaoNovoPedido requsicao){
        return "pedido/formulario";
    }

    @PostMapping("novo")                                     //Resultado da validacao
    public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result){   //Criar um DTO(Data transfer Objetct) para receber os dados da requisição,evitando assim uma falha de segurança chamada Web Parameter Tampering,
        if(result.hasErrors()){
            return "pedido/formulario";
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();  //SecurityContextHolder nos permite pegar o nome do usuario.
        User user = userRepository.findByUsername(username);    //desta forma salvamos o pedido com o usuario logado.
        Pedido pedido = requisicao.toPedido();
        pedido.setUser(user);
        pedidosRepository.save(pedido);
        return "redirect:/home";
    }


}
