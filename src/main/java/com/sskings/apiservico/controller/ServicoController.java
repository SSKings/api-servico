package com.sskings.apiservico.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sskings.apiservico.model.Servico;
import com.sskings.apiservico.service.ServicoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/servicos")
public class ServicoController {
    
    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody Servico servico) {
        servico.setData(LocalDateTime.now(ZoneId.of("UTC-3")));       
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.cadastrar(servico));
    }
    
    @GetMapping
    public ResponseEntity<List<Servico>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.listarServicos()) ;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscar(@PathVariable(value ="id") UUID servicoId ) {
        Optional<Servico> OpServico = servicoService.buscar(servicoId);
        if(!OpServico.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("não encontrado.");      
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(OpServico.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") UUID servicoId, 
                                            @RequestBody Servico servico ){
        Optional<Servico> OpServico = servicoService.buscar(servicoId);
        if(!OpServico.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("não encontrado.");
        }
        var servicoAtualizado = new Servico();
        BeanUtils.copyProperties(servico, servicoAtualizado);
        servicoAtualizado.setId(OpServico.get().getId());
        servicoAtualizado.setData(OpServico.get().getData());
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.atualizar(servicoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable(value = "id") UUID servicoId){
        Optional<Servico> OpServico = servicoService.buscar(servicoId);
        if(!OpServico.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("não encontrado.");
        }
        servicoService.deletar(OpServico.get());
        return ResponseEntity.status(HttpStatus.OK).body("ok, deletado.");
    }
}
