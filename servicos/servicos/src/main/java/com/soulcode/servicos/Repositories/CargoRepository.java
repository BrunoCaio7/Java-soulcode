package com.soulcode.servicos.Repositories;

import com.soulcode.servicos.Models.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository <Cargo, Integer> {
}
