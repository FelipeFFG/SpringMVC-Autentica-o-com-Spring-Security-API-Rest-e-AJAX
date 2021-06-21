package com.example.demo.controller;

import com.example.demo.model.Pedido;
import com.example.demo.model.StatusPedido;
import com.example.demo.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/home")                              //Todas as requisições feitas para "/home" vai bater nesse HomeControler.
public class HomeController {

    @Autowired                                           //pedi para o spring uma instancia de repository
    private PedidosRepository pedidosRepository;         //separando a controladora do repositorio, onde agora para acessar o banco de dados ele tem que chamar a clase PedidosRepository

    @GetMapping                                        //Todas Requisiçoes "/home" vai bater nesse metodo
    public String home(Model model) {
        List<Pedido> pedidos = pedidosRepository.findAll();
        model.addAttribute("pedidos", pedidos);
        return "home";
    }




    @GetMapping("/{status}")                                                         //passando uma variavel que se altera , de acordo com o valor passado
    public String porStatus(@PathVariable("status") String status, Model model) {   //@pathVarivel, passa para a string a variavel escrita na url
        List<Pedido> pedidos = pedidosRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));  //converte a string  em um enum presente no StatusPedido.
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status",status);
        return "home";
    }

    @ExceptionHandler(IllegalArgumentException.class)                                   //Tratando os paths/status que nao forem os que selecioanmos no porStauts.
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
