package com.metadiaria.api.controller;

import com.metadiaria.api.dto.PlanejamentoRequestDTO;
import com.metadiaria.api.dto.PlanejamentoResponseDTO;
import com.metadiaria.api.service.PlanejamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/planejamentos")
@RequiredArgsConstructor
public class PlanejamentoController {

    private final PlanejamentoService planejamentoService;

    @PostMapping
    public ResponseEntity<PlanejamentoResponseDTO> criarPlanejamento(@Valid @RequestBody PlanejamentoRequestDTO dto) {
        PlanejamentoResponseDTO response = planejamentoService.criarPlanejamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<PlanejamentoResponseDTO> buscarPorUsuarioEMes(
            @RequestParam Long usuarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDate mesAno) {
        PlanejamentoResponseDTO response = planejamentoService.buscarPorUsuarioEMes(usuarioId, mesAno);
        return ResponseEntity.ok().body(response);
    }

}
