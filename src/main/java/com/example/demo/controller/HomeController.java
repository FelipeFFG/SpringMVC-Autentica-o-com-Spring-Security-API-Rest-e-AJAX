package com.example.demo.controller;

import com.example.demo.model.Pedido;
import com.example.demo.model.StatusPedido;
import com.example.demo.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PedidosRepository pedidosRepository;

    @GetMapping
    public String home(Model model, Principal principal) {     //Principal para pegar os dados dos usuarios
        List<Pedido> pedidos = pedidosRepository.findAllByUsuario(principal.getName());
        model.addAttribute("pedidos", pedidos);
        return "home";
    }


    @GetMapping("/{status}")
    public String porStatus(@PathVariable("status") String status, Model model) {
        List<Pedido> pedidos = pedidosRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status",status);
        return "home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){                                                            //retornando para o /home
        return "redirect:/home";
    }

/*    @GetMapping("/aguardando")                    //Todas as requisiçoes "/home/aguardando" vai bater nesse metodo
    public String aguardando(Model model){
        List<Pedido> pedidos = pedidosRepository.findByStatus(StatusPedido.AGUARDANDO);
        model.addAttribute("pedidos",pedidos);
        return "home";
    }*/
}
