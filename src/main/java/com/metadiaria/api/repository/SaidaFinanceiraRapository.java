package com.metadiaria.api.repository;

import com.metadiaria.api.entity.DiaPlanejado;
import com.metadiaria.api.entity.SaidaFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaidaFinanceiraRapository extends JpaRepository<SaidaFinanceira, Long> {
    List<SaidaFinanceira> findByDiaPlanejadoId(Long diaPlanejadoId);
}
