package com.metadiaria.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RegistroGanhoRequestDTO {

    @NotNull(message = "Ganho bruto é obrigatorio")
    @PositiveOrZero(message = "Ganho bruto não pode ser negativo")
    private BigDecimal ganhoBruto;
}
