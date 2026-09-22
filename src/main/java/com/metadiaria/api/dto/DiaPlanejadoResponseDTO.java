package com.metadiaria.api.dto;

import com.metadiaria.api.entity.StatusDia;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class DiaPlanejadoResponseDTO {

    private Long id;
    private LocalDate data;
    private Boolean ehDiaTrabalho;
    private BigDecimal metaDiariaAtual;
    private BigDecimal ganhoBruto;
    private StatusDia status;
}
