package com.metadiaria.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SaidaRequestDTO {

    @NotNull(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O Valor deve ser positivo")
    private BigDecimal valor;
}
