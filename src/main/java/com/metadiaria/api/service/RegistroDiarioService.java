package com.metadiaria.api.service;

import com.metadiaria.api.dto.DiaPlanejadoResponseDTO;
import com.metadiaria.api.dto.RegistroGanhoRequestDTO;
import com.metadiaria.api.dto.SaidaRequestDTO;
import com.metadiaria.api.entity.DiaPlanejado;
import com.metadiaria.api.entity.PlanejamentoMensal;
import com.metadiaria.api.entity.SaidaFinanceira;
import com.metadiaria.api.entity.StatusDia;
import com.metadiaria.api.repository.DiaPlanejadoRepository;
import com.metadiaria.api.repository.SaidaFinanceiraRapository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroDiarioService {

    private final DiaPlanejadoRepository diaRepository;
    private final SaidaFinanceiraRapository saidaRepository;
    private final MetaCalculoService metaCalculoService;

    @Transactional
    public DiaPlanejadoResponseDTO registrarGanho(Long diaId, RegistroGanhoRequestDTO dto) {
        DiaPlanejado dia = diaRepository.findById(diaId)
                .orElseThrow(() -> new RuntimeException("Dia planejado não encontrado."));

        // Atualiza o ganho bruto e marca o dia como CONCLUIDO
        dia.setGanhoBruto(dto.getGanhoBruto());
        dia.setStatusDia(StatusDia.CONCLUIDO);
        diaRepository.save(dia);

        // Busca o planejamento e todos os dias do mês para recalcular as metas futuras
        PlanejamentoMensal planejamento = dia.getPlanejamento();
        List<DiaPlanejado> todosOsDias = diaRepository.findByPlanejamentoIdOrderByDataAsc(planejamento.getId());


        metaCalculoService.recalcularMetasFuturas(planejamento, todosOsDias);
        diaRepository.saveAll(todosOsDias);

        return converterParaResponseDTO(dia);
    }

    @Transactional
    public void registrarSaida(Long diaId, SaidaRequestDTO dto) {
        DiaPlanejado dia = diaRepository.findById(diaId)
                .orElseThrow(() -> new RuntimeException("Dia planejado não encontrado."));

        SaidaFinanceira saida = new SaidaFinanceira();
        saida.setDiaPlanejado(dia);
        saida.setDescricao(dto.getDescricao());
        saida.setValor(dto.getValor());

        saidaRepository.save(saida);
    }

    @Transactional
    public DiaPlanejadoResponseDTO alterarStatusDia(Long diaId, StatusDia novoStatus) {
        DiaPlanejado dia = diaRepository.findById(diaId)
                .orElseThrow(() -> new RuntimeException("Dia planejado não encontrado."));

        dia.setStatusDia(novoStatus);

        // Se mudou para FOLGA, indica que não haverá trabalho no dia
        if(novoStatus == StatusDia.FOLGA) {
            dia.setEhDiaTrabalho(false);
        }
        else if(novoStatus == StatusDia.PENDENTE || novoStatus == StatusDia.CONCLUIDO) {
            dia.setEhDiaTrabalho(true);
        }

        diaRepository.save(dia);

        // Recalcula as metas para reajustar com base no novo status
        PlanejamentoMensal planejamento = dia.getPlanejamento();
        List<DiaPlanejado> todosOsDias = diaRepository.findByPlanejamentoIdOrderByDataAsc(planejamento.getId());

        metaCalculoService.recalcularMetasFuturas(planejamento, todosOsDias);
        diaRepository.saveAll(todosOsDias);

        return converterParaResponseDTO(dia);
    }

    private DiaPlanejadoResponseDTO converterParaResponseDTO(DiaPlanejado dia) {
        return DiaPlanejadoResponseDTO.builder()
                .id(dia.getId())
                .data(dia.getData())
                .ehDiaTrabalho(dia.getEhDiaTrabalho())
                .metaDiariaAtual(dia.getMetaDiariaAtual())
                .ganhoBruto(dia.getGanhoBruto())
                .status(dia.getStatusDia())
                .build();
    }
}
