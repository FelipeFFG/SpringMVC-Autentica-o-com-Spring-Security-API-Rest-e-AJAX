package com.example.demo.repository;

import com.example.demo.model.Pedido;
import com.example.demo.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository                                                              //fala pro Spring que essa classe é um repositorio, e que ela deve criar instancias toda vez que alguem pedir.
public interface PedidosRepository extends JpaRepository<Pedido, Long> { //Utilizando SpringDataJPA

    List<Pedido> findByStatus(StatusPedido status);      //Como usamos um metodo que retorna uma lista de pedidos, o SpringDataJPA sabe que vai fazer um select na tabela pedido
                                                         //e como usamos um findby na escrita do metodo, o SpringDATAJPA sabe que precisa adidicionar um filtro WHERE por status
}
