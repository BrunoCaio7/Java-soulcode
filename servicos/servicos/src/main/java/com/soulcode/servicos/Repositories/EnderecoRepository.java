package com.soulcode.servicos.Repositories;

import com.soulcode.servicos.Models.EnderecoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoCliente, Integer> {
}
