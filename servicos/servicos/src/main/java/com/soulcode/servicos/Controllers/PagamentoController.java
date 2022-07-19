package com.soulcode.servicos.Controllers;

import com.soulcode.servicos.Models.Pagamento;
import com.soulcode.servicos.Services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("servicos") // REQUESTMAPPING - define a rota base
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;


    @GetMapping("/pagamentos")
    public List<Pagamento> mostrarTodosPagamentos() {
        List<Pagamento> pagamento = pagamentoService.mostrarTodosPagamentos();
        return pagamento;
    }

    @GetMapping("/pagamentos/{idPagamento}")
    public ResponseEntity<Pagamento> mostrarPagamentoPeloId(@PathVariable Integer idPagamento){
     Pagamento pagamento = pagamentoService.mostrarPagamentoPeloId(idPagamento);
     return ResponseEntity.ok().body(pagamento);
    }

    @GetMapping("/pagamentosPeloStatus")
    public  List<Pagamento> mostrarPagamentosPeloStatus(@RequestParam("status")String status){
        List<Pagamento> pagamentos = pagamentoService.mostrarPagamentosPeloStatus(status);
        return pagamentos;
    }
    @GetMapping("/pagamentosChamadosComCliente")
    public List<List> orcamentoComServicoCliente(){
        List<List> pagamentos = pagamentoService.orcamentoComServicoCliente();
        return pagamentos;
    }


    @PostMapping("/pagamentos/{idChamado}")
    public ResponseEntity<Pagamento> cadastrarPagamento(@PathVariable Integer idChamado,
                                                        @RequestBody Pagamento pagamento) {
        try {

            pagamento = pagamentoService.cadastrarPagamento(pagamento, idChamado);
            URI newUri = ServletUriComponentsBuilder.fromCurrentRequest().path("id")
                    .buildAndExpand(pagamento.getIdPagamento()).toUri();

            return ResponseEntity.created(newUri).body(pagamento);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/pagamentos/{idPagamento}")
    public ResponseEntity<Pagamento> editarPagamento(@PathVariable Integer idPagamento,
                                                     @RequestBody Pagamento pagamento){
        pagamento.setIdPagamento(idPagamento);
        pagamentoService.editarPagamento(pagamento);
        return ResponseEntity.ok().body(pagamento);
    }

    @PutMapping("/pagamentosAlteracaoStatus/{idPagamento}")
    public ResponseEntity<Pagamento> modificarStatusPagamento(@PathVariable Integer idPagamento,
                                                              @RequestParam("status")String status){
        pagamentoService.modificarStatusPagamento(idPagamento,status);
        return ResponseEntity.noContent().build();
    }



}
