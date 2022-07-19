package com.soulcode.servicos.Repositories;

import com.soulcode.servicos.Models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    @Query(value = "SELECT * FROM pagamento WHERE STATUS = :status",nativeQuery = true)
    List<Pagamento> findByStatus (String status);

    @Query(value = "SELECT Pagamento.*,chamado.id_chamado,chamado.titulo,cliente.id_cliente,cliente.nome\n" +
            "FROM chamado RIGHT JOIN pagamento ON chamado.id_chamado = pagamento.id_pagamento\n" +
            "LEFT JOIN cliente ON cliente.id_cliente = chamado.id_cliente;",nativeQuery = true)
    List<List> orcamentoComServicoCliente();


}
