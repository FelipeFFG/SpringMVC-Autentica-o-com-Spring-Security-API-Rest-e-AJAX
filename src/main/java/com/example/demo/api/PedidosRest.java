package com.example.demo.api;


import com.example.demo.model.Pedido;
import com.example.demo.model.StatusPedido;
import com.example.demo.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {

    @Autowired
    private PedidosRepository pedidosRepository;

    @GetMapping("aguardando")
    public List<Pedido> getpedidosAguardandoOfertas(){
        Sort sort =  Sort.by("id").descending();
        PageRequest paginacao = PageRequest.of(0,10,sort);
        return pedidosRepository.findByStatus(StatusPedido.AGUARDANDO,paginacao);
    }

}
