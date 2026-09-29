package com.metadiaria.api.controller;


import com.metadiaria.api.dto.*;
import com.metadiaria.api.entity.StatusDia;
import com.metadiaria.api.service.RegistroDiarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dias")
@RequiredArgsConstructor
public class DiaPlanejadoController {

    private final RegistroDiarioService registroDiarioService;

    @PatchMapping("/{id}/ganho")
    public ResponseEntity<DiaPlanejadoResponseDTO> registrarGanho(
            @PathVariable Long id,
            @Valid @RequestBody RegistroGanhoRequestDTO dto) {

        DiaPlanejadoResponseDTO response = registroDiarioService.registrarGanho(id, dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/saidas")
    public ResponseEntity<DiaPlanejadoResponseDTO> registrarSaida(
            @PathVariable Long id,
            @Valid @RequestBody SaidaRequestDTO dto) {

        registroDiarioService.registrarSaida(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DiaPlanejadoResponseDTO> alternarStatus(
            @PathVariable Long id,
            @RequestParam StatusDia status) {

        DiaPlanejadoResponseDTO response = registroDiarioService.alterarStatusDia(id, status);
        return ResponseEntity.ok(response);
    }
}
