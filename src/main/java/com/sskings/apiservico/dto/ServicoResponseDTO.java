package com.sskings.apiservico.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.sskings.apiservico.model.Servico;

public record ServicoResponseDTO(UUID id, String nome,  BigDecimal valor, LocalDateTime data ) {
    
    public ServicoResponseDTO(Servico servico){
        this(servico.getId(), servico.getNome(), servico.getValor(), servico.getData());
    }
}
