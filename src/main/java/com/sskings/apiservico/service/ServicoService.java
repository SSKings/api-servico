package com.sskings.apiservico.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sskings.apiservico.model.Servico;
import com.sskings.apiservico.repository.ServicoRepository;

import jakarta.transaction.Transactional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Transactional
    public Servico cadastrar(Servico servico){
        return servicoRepository.save(servico);
    }

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscar(UUID servicoId ) {
        return servicoRepository.findById(servicoId);
    }
    
    @Transactional
    public void deletar(Servico servico){
        servicoRepository.delete(servico);
    }

    @Transactional
    public Servico atualizar(Servico servico){
        return servicoRepository.save(servico);
    }
}
