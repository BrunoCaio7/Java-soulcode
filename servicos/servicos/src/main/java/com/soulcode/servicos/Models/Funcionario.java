package com.soulcode.servicos.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuncionario;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100, unique = true)
    private  String email;

    @Column(nullable = true)
    private String foto;


    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Chamado> chamados = new ArrayList<Chamado>();


    @ManyToOne //muitos para um- muitos=many se le com a tabela que estamos.
    // toOne para um no caso refere-se a outra tabela nesse caso cargo
    @JoinColumn(name = "idCargo")// joincolun cria uma nova coluna e o name da o nome a ela nesse caso idcargo
    private Cargo cargo;





    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public Cargo getCargo() { return cargo; }

    public void setCargo(Cargo cargo) { this.cargo = cargo; }
}
