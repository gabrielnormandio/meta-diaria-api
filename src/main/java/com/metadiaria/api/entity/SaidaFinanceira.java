package com.metadiaria.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_saidas_financeiras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaidaFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dia_planejado_id", nullable = false)
    private DiaPlanejado diaPlanejado;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;
}
