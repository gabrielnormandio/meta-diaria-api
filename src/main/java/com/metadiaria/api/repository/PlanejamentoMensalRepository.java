package com.metadiaria.api.repository;

import com.metadiaria.api.entity.PlanejamentoMensal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PlanejamentoMensalRepository extends JpaRepository<PlanejamentoMensal, Long> {
    Optional<PlanejamentoMensal> findByUsuarioIdAndMesAno(Long usuarioId, LocalDate mesAno);
    boolean existsByUsuarioIdAndMesAno(Long usuarioId, LocalDate mesAno);
}
