package com.sskings.apiservico.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServicoRequestDTO(@NotBlank String nome,@NotNull BigDecimal valor) {
    
}
