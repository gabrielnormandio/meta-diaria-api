package com.metadiaria.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PlanejamentoRequestDTO {

    @NotNull(message = "O ID do usuario é obigatório")
    private Long usuarioId;

    @NotNull(message = "O mês e ano são obrigatórios")
    private LocalDate mesAno;

    @NotNull(message = "A meta mensal é obrigatória")
    @Positive(message = "A meta mensal deve ser maior que zero")
    private BigDecimal metaMensal;

    //Lista com as datas que o usuario pretente FOLGAR no mês
    private List<LocalDate> diasFolga;
}
