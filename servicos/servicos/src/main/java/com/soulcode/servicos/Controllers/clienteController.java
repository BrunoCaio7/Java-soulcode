package com.soulcode.servicos.Controllers;

import com.soulcode.servicos.Models.Cliente;
import com.soulcode.servicos.Models.Funcionario;
import com.soulcode.servicos.Services.ClienteService;
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
public class clienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public List< Cliente>mostrarTodosCliente(){
        List<Cliente>cliente= clienteService.mostrarTodosClientes();
        return cliente;
    }

    @GetMapping("/clientes/{idCliente}")
    public ResponseEntity<Cliente> mostrarClientePeloId(@PathVariable Integer idCliente){
        Cliente cliente = clienteService.mostrarUmClientePeloId(idCliente);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/clientesEmail/{email}")
    public ResponseEntity<Cliente>mostrarUmClientePeloEmail(@PathVariable String email){
        Cliente cliente = clienteService.mostrarUmClientePeloEmail(email);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> cadastrarcliente(@RequestBody Cliente cliente){
        // nessa linha 42, o funcionário ja é salvo na tabela do database
        // agora precisamos criar uma URI para esse novo regristro da tabela
        cliente = clienteService.cadastrarCliente(cliente);
        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("id")
                .buildAndExpand(cliente.getIdCliente()).toUri();
        return ResponseEntity.created(novaUri).body(cliente);

    }

    @DeleteMapping("/clientes/{idCliente}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Integer idCliente){
        clienteService.excluirCliente(idCliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/clientes/{idCliente}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable Integer idCliente,
                                                         @RequestBody Cliente cliente){
        cliente.setIdCliente(idCliente);
        clienteService.editarCliente(cliente);
        return ResponseEntity.ok().body(cliente);

    }
}

