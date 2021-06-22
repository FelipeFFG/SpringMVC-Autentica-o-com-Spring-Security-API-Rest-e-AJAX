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
        List<Pedido> pedidos = pedidosRepository.findByStatus(StatusPedido.ENTREGUE);      //Mostrar todos os pedidos entregues.
        model.addAttribute("pedidos", pedidos);
        return "home";
    }

}
