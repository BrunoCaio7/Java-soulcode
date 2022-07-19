package com.soulcode.servicos.Services;

import com.soulcode.servicos.Models.Cliente;

import com.soulcode.servicos.Models.Funcionario;
import com.soulcode.servicos.Repositories.ClienteRepository;
import com.soulcode.servicos.Services.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

   @Cacheable("clienteCache") // so chama o retorno se o cache expirar
    public List<Cliente> mostrarTodosClientes(){
        return clienteRepository.findAll();
    }
    @Cacheable(value = "clientesCache", key = "idCliente")
    public Cliente mostrarUmClientePeloId(Integer idCliente){

        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        return cliente.orElseThrow(
                () -> new EntityNotFoundException("Cliente não cadastrado: " + idCliente)
        );
    }

    public Cliente mostrarUmClientePeloEmail(String email){
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        return cliente.orElseThrow();
    }
    @CachePut(value = "clientesCache", key = "#cliente.idCliente")
    public Cliente cadastrarCliente(Cliente cliente){
        // só por precaução nós vamos colocar o id do funcionário como nulo
        cliente.setIdCliente(null);
        return clienteRepository.save(cliente);
    }
    @CacheEvict(value = "clientesCache", key = "#idCliente", allEntries = true)
    public void excluirCliente(Integer idCliente){
        // mostrarUmClientePeloId(idCliente);
        clienteRepository.deleteById(idCliente);
    }
    @CachePut(value = "clientesCache", key = "#cliente.idCliente")
    public Cliente editarCliente(Cliente cliente){
        return clienteRepository.save(cliente);

    }




}

