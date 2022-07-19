package com.soulcode.servicos.Repositories;

import com.soulcode.servicos.Models.Chamado;
import com.soulcode.servicos.Models.Cliente;
import com.soulcode.servicos.Models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ChamadoRepository  extends JpaRepository<Chamado,Integer> {

    List<Chamado> findByCliente(Optional<Cliente> cliente);

    List<Chamado> findByFuncionario(Optional<Funcionario> funcionario);

    @Query (value = "SELECT * FROM chamado WHERE status =:status", nativeQuery = true)
    List<Chamado> findByStatus(String status);

    @Query(value = "SELECT * FROM chamado WHERE data_entrada BETWEEN :data1 AND :data2", nativeQuery = true)
    List<Chamado> findByIntervaloData(Date data1, Date data2);
}
