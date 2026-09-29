package com.metadiaria.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;
    private Long usuarioId;
    private String nome;
    private String email;

}
