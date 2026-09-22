package com.metadiaria.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class PlanejamentoResponseDTO {

    private Long id;
    private LocalDate mesAno;
    private BigDecimal metaMensal;
    private BigDecimal totalRealizado;
    private BigDecimal progressoPercentual;
    private List<DiaPlanejadoResponseDTO> dias;
}
