package com.metadiaria.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_planejamentos_mensais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanejamentoMensal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "mes_ano", nullable = false)
    private LocalDate mesAno;

    @Column(name = "meta_mensal", nullable = false)
    private BigDecimal metaMensal;

    @Column(name = "criado_em", nullable = false,  updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }
}
