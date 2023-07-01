package com.sskings.apiservico.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sskings.apiservico.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, UUID> {

}