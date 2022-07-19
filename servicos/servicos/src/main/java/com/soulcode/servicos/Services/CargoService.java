package com.soulcode.servicos.Services;


import com.soulcode.servicos.Models.Cargo;
import com.soulcode.servicos.Repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {
    @Autowired
    CargoRepository cargoRepository;
@Cacheable("cargosCache")
    public List<Cargo> mostrarTodosOsCargos() {
        return cargoRepository.findAll();
    }

@Cacheable(value = "cargosCache", key = "#idCargo" )
    public Cargo mostrarUmCargoPeloId(Integer idCargo){
        Optional<Cargo> cargo = cargoRepository.findById(idCargo);
        return cargo.orElseThrow();
    }
@CachePut(value = "cargosCache", key = "#cargo.idCargo")
    public Cargo cadastrarCargo  (Cargo cargo){
        cargo.setIdCargo(null);
        return cargoRepository.save(cargo);
    }
@CachePut(value = "cargosCache", key = "#cargo.idCargo")
    public Cargo editarCargo (Cargo cargo){
        return cargoRepository.save(cargo);
    }
@CacheEvict(value = "cargosCache", key = "#idCargo",allEntries = true)
    public void excluirCargo (Integer idCargo){
        cargoRepository.deleteById(idCargo);
    }





}


