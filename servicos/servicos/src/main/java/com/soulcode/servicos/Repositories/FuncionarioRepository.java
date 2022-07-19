package com.soulcode.servicos.Repositories;

import com.soulcode.servicos.Models.Cargo;
import com.soulcode.servicos.Models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface FuncionarioRepository extends JpaRepository<Funcionario , Integer> {

    Optional<Funcionario> findByEmail(String email);


    @Query(value = "SELECT *FROM funcionario WHERE id_cargo =:idCargo", nativeQuery = true) //id cargo sem espaço
    List<Funcionario> finByCargo(Integer idCargo); // aqui foi criado metodo manual procurar cargo pelo id para isso
    /// foi criado no funcionario service e contolller os metodo para procurar o funcionariopeloCargoid

    // Optional<Funcionario> findByNome

    // Optional<Funcionario> findByNomeAndEmailAndFoto(String nome, String email)

    List<Funcionario> findByCargo(Optional<Cargo> cargo);

}
