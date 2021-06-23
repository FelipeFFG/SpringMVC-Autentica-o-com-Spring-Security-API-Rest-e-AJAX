package com.example.demo.api;

import com.example.demo.dto.RequisicaoNovaOferta;
import com.example.demo.model.Oferta;
import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/ofertas")
public class OfertasRest {

    @Autowired
    private PedidosRepository pedidosRepository;



    @PostMapping
    public Oferta criaOferta(RequisicaoNovaOferta requisicao){
        Optional<Pedido> pedidoBuscado =pedidosRepository.findById(requisicao.getPedidoId());

        if(!pedidoBuscado.isPresent()){
            return null;
        }
        Pedido pedido = pedidoBuscado.get();
        Oferta nova = requisicao.toOferta();

        nova.setPedido(pedido);
        pedido.getOfertas().add(nova);
        pedidosRepository.save(pedido);   //Nao precisa salvar a oferta, pois como os atributos enstao com cascate All ele ja salva automaticamente.
        return nova;

    }
}
