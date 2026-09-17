package com.metadiaria.api.repository;

import com.metadiaria.api.entity.DiaPlanejado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaPlanejadoRepository extends JpaRepository<DiaPlanejado, Long> {
    List<DiaPlanejado> findByPlanejamentoIdOrderByDataAsc(Long planejamentoId);

}
