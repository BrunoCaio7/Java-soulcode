package com.soulcode.servicos.Controllers;

import com.soulcode.servicos.Models.Cargo;
import com.soulcode.servicos.Services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("servicos")// ESTE SERVICOS ESTA LIGANDO AS ROTAS ( TODAS COMEÇAM POR ELAS)
                           // POR CAUSA DO METODO @REQUESTMAPPING
public class CargoController {

    @Autowired
    CargoService cargoService;

    @GetMapping("/cargos")
    public List<Cargo> mostrarTodosOsCargos() {
        List<Cargo> cargos = cargoService.mostrarTodosOsCargos();
        return cargos;
    }

    @GetMapping ("/cargos/{idCargo}")
    public ResponseEntity<Cargo> mostrarUmCargoPeloId(@PathVariable Integer idCargo){
        Cargo cargo = cargoService.mostrarUmCargoPeloId(idCargo);
        return ResponseEntity.ok().body(cargo);
    }

    @PostMapping ("/cargos")
    public ResponseEntity <Cargo> cadastrarCargo (@RequestBody Cargo cargo ){
        cargo = cargoService.cadastrarCargo(cargo);
        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("id")
                .buildAndExpand(cargo.getIdCargo()).toUri();
        return ResponseEntity.created(novaUri).body(cargo);

    }

    @PutMapping ("/cargos/{idCargo}")
    public ResponseEntity <Cargo> editarCargo (@PathVariable Integer idCargo, @RequestBody Cargo cargo){
        cargo.setIdCargo(idCargo);
        cargoService.editarCargo(cargo);
        return ResponseEntity.ok().body(cargo);
    }

    @DeleteMapping ("/cargos/{idCargo}")
    public ResponseEntity <Cargo> exluirCliente (@PathVariable Integer idCargo){
        cargoService.excluirCargo(idCargo);
        return ResponseEntity.noContent().build();
    }



}
