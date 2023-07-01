package com.sskings.apiservico.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sskings.apiservico.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, UUID> {

}