package com.soulcode.servicos.Services;

import com.soulcode.servicos.Models.Cargo;
import com.soulcode.servicos.Models.Chamado;
import com.soulcode.servicos.Models.Cliente;
import com.soulcode.servicos.Models.Funcionario;
import com.soulcode.servicos.Repositories.CargoRepository;
import com.soulcode.servicos.Repositories.FuncionarioRepository;
import com.soulcode.servicos.Services.Exceptions.DataIntegrityViolationException;
import com.soulcode.servicos.Services.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


// quando se fala em serviços, estamos falando dos métodos do crud da tabela.

@Service
public class FuncionarioService {

    //aqui se faz a injeção de dependência

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    CargoRepository cargoRepository;

    // primeiro serviço na tabela de funcionario vai ser a leitura de todos os funcionarios cadastrados.
    // findALL -> método do spring Data JPA -> busca todos os regristos de uma tabela.

    public List<Funcionario> mostrarTodosFuncionarios(){

        return funcionarioRepository.findAll();
    }


    // vamos para mais um serviço relacionado ao funcionário
    // criar um serviço de buscar apenas um funcionário pelo seu ID(chave primária)

    public Funcionario mostrarUmFuncionarioPeloId(Integer idFuncionario){

        Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
        return funcionario.orElseThrow(
                () -> new EntityNotFoundException("Funcionario não cadastrado: " + idFuncionario)
        );
    }

    // vamos criar mais um serviço para buscar um funcionário pelo seu email.

    public Funcionario mostrarUmFuncionarioPeloEmail(String email){
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);
        return funcionario.orElseThrow();
    }

    public List<Funcionario> mostrarTodosFuncionariosDeUmCargo(Integer idCargo){
            Optional<Cargo> cargo = cargoRepository.findById(idCargo);
            return funcionarioRepository.findByCargo(cargo);
    }

    // vamos criar um serviço para cadastrar um novo funcionário
    public Funcionario cadastrarFuncionario(Funcionario funcionario, Integer idCargo){
       // só por precaução nós vamos colocar o id do funcionário como nulo
       funcionario.setIdFuncionario(null);
        try {
            Cargo cargo = cargoRepository.findById(idCargo).get();
            funcionario.setCargo(cargo);
            return funcionarioRepository.save(funcionario);
        }catch (Exception e){
            throw new DataIntegrityViolationException("Erro ao cadastrar funcionário");
        }

    }

    public void excluirFuncionario(Integer idFuncionario){
       // mostrarUmFuncionarioPeloId(idFuncionario);
        funcionarioRepository.deleteById(idFuncionario);
    }

    public Funcionario editarFuncionario(Funcionario funcionario){

        return funcionarioRepository.save(funcionario);
    }

    public  Funcionario salvarFoto(Integer idFuncionario, String caminhoFoto){
        Funcionario funcionario = mostrarUmFuncionarioPeloId(idFuncionario);
        funcionario.setFoto(caminhoFoto);
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> mostrarFuncionarioPeloIdCargo (Integer idCargo){
        return funcionarioRepository.finByCargo(idCargo);
    }



}
