package com.example.demo.controller;

import com.example.demo.model.Pedido;
import com.example.demo.model.StatusPedido;
import com.example.demo.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController {


    @Autowired
    private PedidosRepository pedidosRepository;


    @GetMapping("pedido")
    public String home(Model model, Principal principal) {     //Principal para pegar os dados dos usuarios
        List<Pedido> pedidos = pedidosRepository.findAllByUsuario(principal.getName());
        model.addAttribute("pedidos", pedidos);
        return "usuario/home";
    }



    @GetMapping("pedido/{status}")
    public String porStatus(@PathVariable("status") String status, Model model,Principal principal) {
        List<Pedido> pedidos = pedidosRepository.findByStatusEUsuario(StatusPedido.valueOf(status.toUpperCase()),principal.getName());
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status",status);
        return "usuario/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){                                                            //retornando para o /home
        return "redirect:/usuario/home";
    }


}
