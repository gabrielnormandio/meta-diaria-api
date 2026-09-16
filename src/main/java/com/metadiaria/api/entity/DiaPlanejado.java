package com.metadiaria.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_dias_planejados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaPlanejado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planejamento_id", nullable = false)
    private PlanejamentoMensal planejamento;

    @Column(name = "data",  nullable = false)
    private LocalDate data;

    @Column(name = "eh_dia_trabalho", nullable = false)
    private Boolean ehDiaTrabalho;

    @Column(name = "meta_diaria_atual", nullable = false)
    private BigDecimal metaDiariaAtual;

    @Column(name = "ganho_bruto", nullable = false)
    private BigDecimal ganhoBruto;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusDia statusDia;
}
