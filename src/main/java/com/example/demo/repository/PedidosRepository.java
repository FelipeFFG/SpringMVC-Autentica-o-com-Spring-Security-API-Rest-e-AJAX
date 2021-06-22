package com.example.demo.repository;

import com.example.demo.model.Pedido;
import com.example.demo.model.StatusPedido;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository                                                              //fala pro Spring que essa classe é um repositorio, e que ela deve criar instancias toda vez que alguem pedir.
public interface PedidosRepository extends JpaRepository<Pedido, Long> { //Utilizando SpringDataJPA

    List<Pedido> findByStatus(StatusPedido status, Pageable paginacao);      //Como usamos um metodo que retorna uma lista de pedidos, o SpringDataJPA sabe que vai fazer um select na tabela pedido


    @Query("select p from Pedido p join p.user u where u.username = :username")
    List<Pedido> findAllByUsuario(@Param("username") String username);




    @Query("select p from Pedido p join p.user u where u.username = :username and p.status =:status")
    List<Pedido> findByStatusEUsuario(@Param("status") StatusPedido status,@Param("username")String username);
}
