package com.metadiaria.api.service;

import com.metadiaria.api.dto.DiaPlanejadoResponseDTO;
import com.metadiaria.api.dto.PlanejamentoRequestDTO;
import com.metadiaria.api.dto.PlanejamentoResponseDTO;
import com.metadiaria.api.entity.DiaPlanejado;
import com.metadiaria.api.entity.PlanejamentoMensal;
import com.metadiaria.api.entity.StatusDia;
import com.metadiaria.api.entity.Usuario;
import com.metadiaria.api.repository.DiaPlanejadoRepository;
import com.metadiaria.api.repository.PlanejamentoMensalRepository;
import com.metadiaria.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanejamentoService {

    private final PlanejamentoMensalRepository planejamentoRepository;
    private final DiaPlanejadoRepository diaRepository;
    private final UsuarioRepository usuarioRepository;
    private final MetaCalculoService metaCalculoService;

    @Transactional
    public PlanejamentoResponseDTO criarPlanejamento(PlanejamentoRequestDTO dto) {
        // Validar se o usuário existe
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        // Validar se já existe planejamento para este mês/ano
        LocalDate primeiroDiaMes = dto.getMesAno().withDayOfMonth(1);
        if(planejamentoRepository.existsByUsuarioIdAndMesAno(usuario.getId(), primeiroDiaMes)) {
            new RuntimeException("Já existe um planejamento cadastrado para esta mês.");
        }

        // 1. Salvar planejamento
        PlanejamentoMensal planejamento = new PlanejamentoMensal();
        planejamento.setUsuario(usuario);
        planejamento.setMesAno(primeiroDiaMes);
        planejamento.setMetaMensal(dto.getMetaMensal());
        planejamento = planejamentoRepository.save(planejamento);

        // 2. Gerar todos os dias do mês
        YearMonth yearMonth = YearMonth.from(primeiroDiaMes);
        int totalDiasNoMes = yearMonth.lengthOfMonth();
        List<DiaPlanejado> diasParaSalvar = new ArrayList<>();

        List<LocalDate> folgas = dto.getDiasFolga() != null ? dto.getDiasFolga() : List.of();

        for(int dia = 1; dia <= totalDiasNoMes; dia++) {
            LocalDate dataAtual = yearMonth.atDay(dia);
            boolean ehFolga = folgas.contains(dataAtual);

            DiaPlanejado diaPlanejado = new DiaPlanejado();
            diaPlanejado.setPlanejamento(planejamento);
            diaPlanejado.setData(dataAtual);
            diaPlanejado.setEhDiaTrabalho(!ehFolga);
            diaPlanejado.setGanhoBruto(BigDecimal.ZERO);
            diaPlanejado.setMetaDiariaAtual(BigDecimal.ZERO);
            diaPlanejado.setStatusDia(ehFolga ? StatusDia.FOLGA : StatusDia.PENDENTE);

            diasParaSalvar.add(diaPlanejado);
        }

        // 3. Aplicar o cálculo da meta inicial
        metaCalculoService.recalcularMetasFuturas(planejamento, diasParaSalvar);

        // 4. Salvar todos os dias no banco de dados
        List<DiaPlanejado> diasSalvos = diaRepository.saveAll(diasParaSalvar);

        return converterParaResponseDTO(planejamento, diasSalvos);
    }

    @Transactional(readOnly = true)
    public PlanejamentoResponseDTO buscarPorUsuarioEMes(Long usuarioId, LocalDate mesAno) {
        LocalDate primeiroDiaMes = mesAno.withDayOfMonth(1);
        PlanejamentoMensal planejamento = planejamentoRepository.findByUsuarioIdAndMesAno(usuarioId, primeiroDiaMes)
                .orElseThrow(() -> new RuntimeException("Planejamento não encontrado para o mês informado."));

        List<DiaPlanejado> dias = diaRepository.findByPlanejamentoIdOrderByDataAsc(planejamento.getId());
        return converterParaResponseDTO(planejamento, dias);
    }

    private PlanejamentoResponseDTO converterParaResponseDTO(PlanejamentoMensal planejamento, List<DiaPlanejado> dias) {
        BigDecimal totalRealizado = dias.stream()
                .filter(d -> d.getStatusDia() == StatusDia.CONCLUIDO)
                .map(DiaPlanejado::getGanhoBruto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal progresso = BigDecimal.ZERO;
        if(planejamento.getMetaMensal().compareTo(BigDecimal.ZERO) > 0) {
            progresso = totalRealizado
                    .multiply(BigDecimal.valueOf(100))
                    .divide(planejamento.getMetaMensal(), 2, RoundingMode.HALF_UP);
        }

        List<DiaPlanejadoResponseDTO> diasDTO = dias.stream()
                .map(d -> DiaPlanejadoResponseDTO.builder()
                        .id(d.getId())
                        .data(d.getData())
                        .ehDiaTrabalho(d.getEhDiaTrabalho())
                        .metaDiariaAtual(d.getMetaDiariaAtual())
                        .ganhoBruto(d.getGanhoBruto())
                        .status(d.getStatusDia())
                        .build())
                .toList();

        return PlanejamentoResponseDTO.builder()
                .id(planejamento.getId())
                .mesAno(planejamento.getMesAno())
                .metaMensal(planejamento.getMetaMensal())
                .totalRealizado(totalRealizado)
                .progressoPercentual(progresso)
                .dias(diasDTO)
                .build();
    }

}
