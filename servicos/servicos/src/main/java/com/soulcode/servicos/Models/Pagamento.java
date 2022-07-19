package com.soulcode.servicos.Models;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;

@Entity // PARA CRIAR A TABELA NO MYSQL
public class Pagamento {
// ABAIXO OS ATRIBUTOS ONDE DA OS NOMES DAS COLUNAS DA TABELA
    @Id
    private Integer idPagamento;

    @NumberFormat(pattern = "#.##0,00")
    @Column(nullable = false)
    private double valor;

    @Column(nullable = false , length = 100) // COLUMN PARA DIZER QUE É UMA COLUNA
    private String formaPagamento;


    @Enumerated(EnumType.STRING) // ENUMERATED PARA LIGAR A CLASSE ENUM
    private StatusPagamento status;




// abaixo segue os GETERS E SETERS
    public Integer getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }
}
