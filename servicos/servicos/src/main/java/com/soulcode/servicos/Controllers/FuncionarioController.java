package com.soulcode.servicos.Controllers;

import com.soulcode.servicos.Models.Funcionario;
import com.soulcode.servicos.Services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("servicos")

public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/funcionarios")
    public List<Funcionario> mostrarTodosFuncionarios(){
        List<Funcionario>funcionarios = funcionarioService.mostrarTodosFuncionarios();
        return funcionarios;
    }

    @GetMapping("/funcionarios/{idFuncionario}")
    public ResponseEntity<Funcionario> mostrarFuncionarioPeloId(@PathVariable Integer idFuncionario){
        Funcionario funcionario = funcionarioService.mostrarUmFuncionarioPeloId(idFuncionario);
        return ResponseEntity.ok().body(funcionario);
    }

    @GetMapping("/funcionariosEmail/{email}")
    public ResponseEntity<Funcionario>mostrarUmFuncionarioPeloEmail(@PathVariable String email){
        Funcionario funcionario = funcionarioService.mostrarUmFuncionarioPeloEmail(email);
        return ResponseEntity.ok().body(funcionario);
    }

    @GetMapping("funcionariosDoCargo/{idCargo}")
    public List<Funcionario> mostrarTodosFuncionariosDeUmCargo(@PathVariable Integer idCargo){
        List<Funcionario> funcionarios = funcionarioService.mostrarTodosFuncionariosDeUmCargo(idCargo);
        return funcionarios;
    }

    @PostMapping("/funcionarios/{idCargo}")
    public ResponseEntity<Funcionario> cadastrarFuncionario(@PathVariable Integer idCargo, @RequestBody Funcionario funcionario){
        // nessa linha 42, o funcionário ja é salvo na tabela do database
        // agora precisamos criar uma URI para esse novo regristro da tabela
       funcionario = funcionarioService.cadastrarFuncionario(funcionario,idCargo);
       URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("id")
               .buildAndExpand(funcionario.getIdFuncionario()).toUri();
       return ResponseEntity.created(novaUri).body(funcionario);

    }

    @DeleteMapping("/funcionarios/{idFuncionario}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Integer idFuncionario){
        funcionarioService.excluirFuncionario(idFuncionario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/funcionarios/{idFuncionario}")
    public ResponseEntity<Funcionario> editarFuncionario(@PathVariable Integer idFuncionario,
                                                         @RequestBody Funcionario funcionario){
       funcionario.setIdFuncionario(idFuncionario);
       funcionarioService.editarFuncionario(funcionario);
       return ResponseEntity.ok().body(funcionario);

    }

    @GetMapping("/funcionarioCargo/{idCargo}")
    public List<Funcionario> mostrarFuncionarioPeloIdCargo (@PathVariable Integer idCargo){
        List<Funcionario> funcionarios = funcionarioService.mostrarFuncionarioPeloIdCargo(idCargo);
        return funcionarios;
    }
}
