package com.metadiaria.api.service;

import com.metadiaria.api.entity.DiaPlanejado;
import com.metadiaria.api.entity.PlanejamentoMensal;
import com.metadiaria.api.entity.StatusDia;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class MetaCalculoService {

    public void recalcularMetasFuturas (PlanejamentoMensal planejamento, List<DiaPlanejado> dias) {

        // 1. Somar tudo o que já foi faturado nos dias CONCLUÍDO.
        BigDecimal totalFaturado = dias.stream()
                .filter(d -> d.getStatusDia() == StatusDia.CONCLUIDO)
                .map(DiaPlanejado::getGanhoBruto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 2. Calcular quanto ainda falta para atingir a meta mensal
        BigDecimal saldoRestante = planejamento.getMetaMensal().subtract(totalFaturado);

        // Se já bateu a meta, o saldo restante não pode ser negativo no cálculo das diárias
        if(saldoRestante.compareTo(BigDecimal.ZERO) < 0) {
            saldoRestante = BigDecimal.ZERO;
        }

        // 3. Contar quantos dias de trabalho ainda estão PENDENTES
        Long diasTrabalhoPendentes = dias.stream()
                .filter(d -> d.getEhDiaTrabalho() && d.getStatusDia() == StatusDia.PENDENTE)
                .count();

        // 4. Calcular a nova meta diária proporcional
        BigDecimal novaMetaDiaria = BigDecimal.ZERO;
        if(diasTrabalhoPendentes > 0) {
            novaMetaDiaria = saldoRestante.divide(
                    BigDecimal.valueOf(diasTrabalhoPendentes)
                    ,2,
                    RoundingMode.HALF_UP
            );
        }

        // 5. Atualizar a metaDiariaAtual de todos os dias PENDENTES que são de trabalho
        for(DiaPlanejado dia : dias) {
            if(dia.getEhDiaTrabalho() && dia.getStatusDia() == StatusDia.PENDENTE) {
                dia.setMetaDiariaAtual(novaMetaDiaria);
            }
        }

    }
}
