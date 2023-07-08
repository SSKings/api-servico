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

import com.sskings.apiservico.dto.ServicoRequestDTO;
import com.sskings.apiservico.dto.ServicoResponseDTO;
import com.sskings.apiservico.model.Servico;
import com.sskings.apiservico.service.ServicoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/servicos")
public class ServicoController {
    
    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> cadastrar(@RequestBody @Valid ServicoRequestDTO obj) {
        var servico = new Servico(obj);
        servico.setData(LocalDateTime.now(ZoneId.of("UTC-3")));        
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.cadastrar(servico));
    }
    
    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(
            servicoService.listarServicos().
            stream().map(ServicoResponseDTO::new).toList()
        );
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscar(@PathVariable(value ="id") UUID servicoId ) {
        Optional<Servico> OpServico = servicoService.buscar(servicoId);
        if(!OpServico.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("não encontrado");      
        }

        var response = OpServico.get();
        return ResponseEntity.status(HttpStatus.FOUND).body(new ServicoResponseDTO(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") UUID servicoId, 
                                            @RequestBody ServicoRequestDTO dto ){
        Optional<Servico> OpServico = servicoService.buscar(servicoId);
        if(!OpServico.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("não encontrado.");
        }

        var servicoAtualizado = new Servico();
        BeanUtils.copyProperties(dto, servicoAtualizado);
        servicoAtualizado.setId(OpServico.get().getId());
        servicoAtualizado.setData(OpServico.get().getData());
        servicoAtualizado = servicoService.atualizar(servicoAtualizado);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ServicoResponseDTO(servicoAtualizado));
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
