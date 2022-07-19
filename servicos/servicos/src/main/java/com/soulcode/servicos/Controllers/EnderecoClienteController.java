package com.soulcode.servicos.Controllers;


import com.soulcode.servicos.Models.EnderecoCliente;
import com.soulcode.servicos.Services.EnderecoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class EnderecoClienteController {

    @Autowired
    EnderecoClienteService enderecoClienteService;

    @GetMapping("/enderecoCliente")
    public List<EnderecoCliente> mostrarTodosEnderecos(){
        List<EnderecoCliente> enderecos = enderecoClienteService.mostrarTodosEnderecos();
        return enderecos;
    }

    @GetMapping ("/enderecoCliente/{idEndereco}")
    public ResponseEntity<EnderecoCliente> mostrarUmEnderecoPeloId(@PathVariable Integer idEndereco){
        EnderecoCliente enderecoCliente = enderecoClienteService.mostrarUmEnderecoPeloId(idEndereco);
        return ResponseEntity.ok().body(enderecoCliente);
    }

    @PostMapping("/enderecoCliente/{idCliente}")
    public ResponseEntity<EnderecoCliente> cadastrarEnderecoDoCliente(@PathVariable Integer idCliente,
                                                                      @RequestBody EnderecoCliente enderecoCliente){

        try {
            enderecoCliente = enderecoClienteService.cadastrarEnderecoDoCliente(enderecoCliente,idCliente);
            URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(enderecoCliente.getIdEndereco()).toUri();

            return ResponseEntity.created(novaUri).body(enderecoCliente);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @PutMapping("/enderecoCliente/{idEndereco}")
    public ResponseEntity<EnderecoCliente> editarEndereco(@PathVariable Integer idEndereco,
                                                          @RequestBody EnderecoCliente enderecoCliente){
        enderecoCliente.setIdEndereco(idEndereco);
        enderecoClienteService.editarEndereco(enderecoCliente);
        return ResponseEntity.ok().body(enderecoCliente);
    }
}
