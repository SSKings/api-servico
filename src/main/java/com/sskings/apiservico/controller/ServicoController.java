package com.sskings.apiservico.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sskings.apiservico.model.Servico;
import com.sskings.apiservico.service.ServicoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/servicos")
public class ServicoController {
    
    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public Servico cadastrar(@RequestBody Servico servico) {
        servico.setData(LocalDateTime.now(ZoneId.of("UTC")));       
        return servicoService.cadastrar(servico);
    }
    
    @GetMapping
    public List<Servico> listar() {
        return servicoService.listarServicos();
    }
    
}
