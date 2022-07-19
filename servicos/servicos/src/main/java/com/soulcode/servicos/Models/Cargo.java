package com.soulcode.servicos.Models;

// 1 primeiro passo MODELS
// CRIAR ATRIBUBUTOS
// 2 PASSO CRIAR INTERFACE (REPOSITORY)
// 3 PASSO SERVICE
// 4 PASSO CONTROLLER

import javax.persistence.*;


@Entity  // @Entity: representa tabela que será criado no MySQL. Indica que a classe
// é uma entidade, que é uma tabela e cada instancia dessa entidade é uma linha do banco de dados
public class Cargo {

    @Id  // CHAVE PRIMARIA
    @GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue(strategy = GenerationType.IDENTITY):
    // Diz ao provedor de persistência que a chave será gerada de forma automática, em seguida indicar a estratégia.
    // IDENTITY
    private Integer idCargo;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false)
    private double salario;





    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
